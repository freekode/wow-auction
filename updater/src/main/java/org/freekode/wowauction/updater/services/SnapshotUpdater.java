package org.freekode.wowauction.updater.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.freekode.wowauction.updater.dao.interfaces.BidDAO;
import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.freekode.wowauction.updater.dao.interfaces.RealmDAO;
import org.freekode.wowauction.updater.dao.interfaces.SnapshotDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class SnapshotUpdater {
    private static final Logger logger = LogManager.getLogger(SnapshotUpdater.class);

    @Autowired
    private SnapshotDAO snapshotDAO;

    @Autowired
    private BidDAO bidDAO;

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

            SnapshotEntity lastSnapshot = snapshotDAO.getLast(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot = " + newSnapshot);


                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("full auction list size = " + auctionList.size());


                logger.info("conversion to entities");
                Set<BidEntity> refreshedBids = new HashSet<>(EntityConversion.convertToBids(auctionList));

                Set<BidEntity> persistedBids = new HashSet<>(bidDAO.findBySnapshot(lastSnapshot));
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
                newSnapshot = snapshotDAO.save(newSnapshot);
                logger.info("save snapshot = " + newSnapshot);


                List<ItemEntity> newItems = EntityConversion.getItemsWithoutBids(newBids);
                logger.info("new items = " + newItems.size());


                logger.info("close the bids");
                bidDAO.closeAll(closedBids);


                // add for still existing bids new snapshot
                for (BidEntity bid : existingBids) {
                    Set<SnapshotEntity> snapshots = new HashSet<>(snapshotDAO.findByBid(bid));
                    snapshots.add(newSnapshot);
                    bid.setSnapshots(snapshots);
                }

                // add snapshot for new bids
                for (BidEntity bid : newBids) {
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));
                }

                // update existing bids and their snapshots
                logger.info("save existing bids");
                bidDAO.saveAll(existingBids);


                logger.info("update or create items");
                List<ItemEntity> updatedItems = itemDAO.saveAll(newItems);


                // make a relationship between new bid and item
                // be cautious new bids can may have connection with existing items
                // so it is critical that addedItems was really full list of items
                for (BidEntity bid : newBids) {
                    ItemEntity bidItem = bid.getItem();
                    for (ItemEntity dbItem : updatedItems) {
                        if (dbItem.equals(bidItem)) {
                            bid.setItem(dbItem);
                        }
                    }
                }


                // save new bids with connection
                logger.info("save new bids");
                bidDAO.saveAll(newBids);


                // ok, now retrieve additional info about items
                logger.info("wowhead = " + WowheadAPI.getItemInfo(newItems.get(0).getIdentifier()));
            }
        }

        logger.info("auction update ended");
    }
}
