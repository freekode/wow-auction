package org.freekode.wowauction.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.beans.interfaces.BidBean;
import org.freekode.wowauction.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.*;
import org.freekode.wowauction.tools.EntityConversion;
import org.freekode.wowauction.tools.WorldOfWarcraftAPI;
import org.freekode.wowauction.tools.WowheadAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SnapshotUpdater {
    private static final Logger logger = LogManager.getLogger(SnapshotUpdater.class);

    @Autowired
    private SnapshotBean snapshotBean;

    @Autowired
    private BidBean bidBean;

    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private RealmDAO realmDAO;


    public void scheduleUpdate() {
        updateAuction();
    }

    public void updateAuction() {
        logger.info("auction update started");

        for (RealmEntity realm : realmDAO.findForUpdate()) {
            logger.info("realm = " + realm);

            Map<String, String> newSnapshotMap = WorldOfWarcraftAPI.getSnapshot(realm.getRegion().toString(), realm.getSlug());
            SnapshotEntity newSnapshot = new SnapshotEntity();
            newSnapshot.setRealm(realm);
            newSnapshot.setFile(newSnapshotMap.get("url"));
            newSnapshot.setLastModified(new Date(new Long(newSnapshotMap.get("lastModified"))));

            SnapshotEntity lastSnapshot = snapshotBean.getLastEntity(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot = " + newSnapshot);


                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("full auction list size = " + auctionList.size());


                logger.info("conversion to entities");
                Set<BidEntity> refreshedBids = new HashSet<>(EntityConversion.convertToBids(auctionList));

                Set<BidEntity> persistedBids = new HashSet<>(bidBean.findBySnapshot(lastSnapshot));
                logger.info("loaded from db = " + persistedBids.size());


                // separate bids (closed, existing, new)
                List<BidEntity> closedBids = new ArrayList<>(persistedBids);
                closedBids.removeAll(refreshedBids);
                logger.info("closed bids = " + closedBids.size());

                List<BidEntity> existingBids = new ArrayList<>(persistedBids);
                existingBids.retainAll(refreshedBids);
                logger.info("already existing bids = " + existingBids.size());

                List<BidEntity> newBids = new ArrayList<>(refreshedBids);
                newBids.removeAll(persistedBids);
                logger.info("new bids = " + newBids.size());


                newSnapshot.setClosed(closedBids.size());
                newSnapshot.setExisting(existingBids.size());
                newSnapshot.setNewAmount(newBids.size());
                newSnapshot = snapshotBean.save(newSnapshot);
                logger.info("save snapshot = " + newSnapshot);


                List<ItemEntity> newItems = EntityConversion.getItemsWithoutBids(newBids);
                logger.info("new items = " + newItems.size());


                logger.info("close the bids");
                bidBean.closeAll(closedBids);


                // add for still existing bids new snapshot
                for (BidEntity bid : existingBids) {
                    Set<SnapshotEntity> snapshots = new HashSet<>(snapshotBean.findByBid(bid));
                    snapshots.add(newSnapshot);
                    bid.setSnapshots(snapshots);
                }

                // add snapshot for new bids
                for (BidEntity bid : newBids) {
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));
                }

                // update existing bids and their snapshots
                logger.info("save existing bids");
                bidBean.saveAll(existingBids);


                logger.info("update items");
                List<ItemEntity> updatedItems = itemDAO.updateAll(newItems);

                logger.info("create items");
                List<ItemEntity> createdItems = itemDAO.createAll(newItems);


                // make a relationship between new bid and item
                // be cautious new bids can may have connection with existing items
                // so it is critical that addedItems was really full list of items
                List<ItemEntity> allUpdatedItems = new ArrayList<>();
                allUpdatedItems.addAll(updatedItems);
                allUpdatedItems.addAll(createdItems);
                for (BidEntity bid : newBids) {
                    ItemEntity bidItem = bid.getItem();
                    for (ItemEntity dbItem : allUpdatedItems) {
                        if (dbItem.equals(bidItem)) {
                            bid.setItem(dbItem);
                        }
                    }
                }


                // save new bids with connection
                logger.info("save new bids");
                bidBean.saveAll(newBids);


                // ok, now retrieve additional info about items
                logger.info("get info about new items");
                for (ItemEntity item : createdItems) {
                    Map<String, String> infoMap = WowheadAPI.getItemInfo(item.getIdentifier());

                    ItemInfoEntity info = new ItemInfoEntity();
                    info.setName(infoMap.get("name"));
                    info.setLevel(infoMap.get("level"));
                    info.setUrl(infoMap.get("link"));
                    info.setIcon(infoMap.get("icon") + ".jpg");
                    info.setItem(item);

                    item.setItemInfo(info);
                }

                logger.info("save items information");
                itemDAO.updateAll(createdItems);
            }
        }

        logger.info("auction update ended");
    }
}
