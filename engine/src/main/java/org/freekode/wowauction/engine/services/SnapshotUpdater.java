package org.freekode.wowauction.engine.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.engine.beans.interfaces.BidBean;
import org.freekode.wowauction.engine.beans.interfaces.ItemBean;
import org.freekode.wowauction.engine.beans.interfaces.RealmBean;
import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
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


    @Scheduled(cron = "0 */10 * * * ?")
    public void scheduleUpdate() {
        updateAuction();
    }

    public void updateAuction() {
        logger.info("update started");
        for (Realm realm : realmBean.findForUpdate()) {
            logger.info("realm = " + realm);

            Map<String, String> newSnapshotMap = WorldOfWarcraftAPI.getSnapshot(realm.getRegion().toString(), realm.getSlug());
            Snapshot newSnapshot = new Snapshot();
            newSnapshot.setRealm(realm);
            newSnapshot.setFile(newSnapshotMap.get("url"));
            newSnapshot.setLastModified(new Date(new Long(newSnapshotMap.get("lastModified"))));

            Snapshot lastSnapshot = snapshotBean.getLastByRealm(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot = " + newSnapshot);


                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("auctionList size = " + auctionList.size());


                Set<Bid> refreshedBids = EntityConversion.convertToBids(auctionList);
                Set<Bid> persistedBids = new HashSet<>(bidBean.findBySnapshot(lastSnapshot));


                Set<Bid> closedBids = new HashSet<>(persistedBids);
                closedBids.removeAll(refreshedBids);
                newSnapshot.setClosed(closedBids.size());
                logger.info("bids closed = " + closedBids.size());


                Set<Bid> existingBids = new HashSet<>(persistedBids);
                existingBids.retainAll(refreshedBids);
                newSnapshot.setExisting(existingBids.size());
                logger.info("bids already exist = " + existingBids.size());


                Set<Bid> newBids = new HashSet<>(refreshedBids);
                newBids.removeAll(persistedBids);
                newSnapshot.setNewAmount(newBids.size());
                logger.info("bids new = " + newBids.size());

                Set<Item> newItems = EntityConversion.getItemsWithoutBids(newBids);
                logger.info("items = " + newItems.size());


                newSnapshot = snapshotBean.save(newSnapshot);


                for (Bid bid : closedBids)
                    bidBean.closeBid(bid);

                for (Bid bid : existingBids) {
                    Set<Snapshot> snapshots = new HashSet<>(snapshotBean.getByBid(bid));
                    snapshots.add(newSnapshot);
                    bid.setSnapshots(snapshots);
                }

                for (Bid bid : newBids)
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));

                bidBean.saveAll(existingBids);


                Set<Item> addedItems = itemBean.updateOrCreateAll(newItems);


                for (Bid bid : newBids) {
                    Item bidItem = bid.getItem();
                    for (Item dbItem : addedItems) {
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
