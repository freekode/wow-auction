package org.freekode.wowauction.updater.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.updater.beans.interfaces.BidBean;
import org.freekode.wowauction.updater.beans.interfaces.ItemBean;
import org.freekode.wowauction.updater.beans.interfaces.RealmBean;
import org.freekode.wowauction.updater.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SnapshotUpdater {
    private static final Logger logger = LogManager.getLogger(SnapshotUpdater.class);

    @Autowired
    private RealmBean realmBean;

    @Autowired
    private ItemBean itemBean;

    @Autowired
    private BidBean bidBean;

    @Autowired
    private SnapshotBean snapshotBean;


    public void scheduleUpdate() {
        updateAuction();
    }

    public void updateAuction() {
        for (RealmEntity realm : realmBean.findForUpdate()) {
            logger.info("realm = " + realm);

            Map<String, String> newSnapshotMap = WorldOfWarcraftAPI.getSnapshot(realm.getRegion().toString(), realm.getSlug());
            SnapshotEntity newSnapshot = new SnapshotEntity();
            newSnapshot.setRealm(realm);
            newSnapshot.setFile(newSnapshotMap.get("url"));
            newSnapshot.setLastModified(new Date(new Long(newSnapshotMap.get("lastModified"))));

            SnapshotEntity lastSnapshot = snapshotBean.getLastByRealm(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot = " + newSnapshot);


                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("auctionList size = " + auctionList.size());


                Set<BidEntity> refreshedBids = EntityConversion.convertToBids(auctionList);
                Set<BidEntity> persistedBids = new HashSet<>(bidBean.findBySnapshot(lastSnapshot));


                Set<BidEntity> closedBids = new HashSet<>(persistedBids);
                closedBids.removeAll(refreshedBids);
                newSnapshot.setClosed(closedBids.size());
                logger.info("bids closed = " + closedBids.size());


                Set<BidEntity> existingBids = new HashSet<>(persistedBids);
                existingBids.retainAll(refreshedBids);
                newSnapshot.setExisting(existingBids.size());
                logger.info("bids already exist = " + existingBids.size());


                Set<BidEntity> newBids = new HashSet<>(refreshedBids);
                newBids.removeAll(persistedBids);
                newSnapshot.setNewAmount(newBids.size());
                logger.info("bids new = " + newBids.size());

                Set<ItemEntity> newItems = EntityConversion.getItemsWithoutBids(newBids);
                logger.info("items = " + newItems.size());


                newSnapshot = snapshotBean.save(newSnapshot);


                for (BidEntity bid : closedBids)
                    bidBean.closeBid(bid);

                for (BidEntity bid : existingBids) {
                    Set<SnapshotEntity> snapshots = new HashSet<>(snapshotBean.getByBid(bid));
                    snapshots.add(newSnapshot);
                    bid.setSnapshots(snapshots);
                }

                for (BidEntity bid : newBids)
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));

                bidBean.saveAll(existingBids);


                Set<ItemEntity> addedItems = itemBean.updateOrCreateAll(newItems);


                for (BidEntity bid : newBids) {
                    ItemEntity bidItem = bid.getItem();
                    for (ItemEntity dbItem : addedItems) {
                        if (dbItem.equals(bidItem)) {
                            bid.setItem(dbItem);
                        }
                    }
                }
                bidBean.saveAll(newBids);
            }
        }

        logger.info("update ended");
    }
}
