package org.freekode.wowauction.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Bid;
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
    private SnapshotDAO snapshotDAO;

    @Autowired
    private RealmDAO realmDAO;

    @Autowired
    private BidDAO bidDAO;


    @Scheduled(cron = "0 */5 * * * ?")
    public void scheduleUpdate() {
        updateAuction();
    }

    public void updateAuction() {
        logger.info("update started");
        for (Realm realm : realmDAO.findAll()) {
            if (!realm.getUpdating()) {
                continue;
            }

            logger.info("realm = " + realm);

            Map<String, String> newSnapshotMap = WorldOfWarcraftAPI.getSnapshot(realm.getRegion().toString(), realm.getSlug());
            Snapshot newSnapshot = new Snapshot();
            newSnapshot.setRealm(realm);
            newSnapshot.setFile(newSnapshotMap.get("url"));
            newSnapshot.setLastModified(new Date(new Long(newSnapshotMap.get("lastModified"))));

            Snapshot lastSnapshot = snapshotDAO.getLast(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("auctionList size = " + auctionList.size());

                newSnapshot.setSize(auctionList.size());
                snapshotDAO.create(newSnapshot);
                logger.info("newSnapshot = " + newSnapshot);


                Set<Bid> refreshedBids = EntityConversion.convertToBids(auctionList);
                Set<Bid> persistedBids = new HashSet<>(bidDAO.findBySnapshot(lastSnapshot));


                Set<Bid> closedBids = new HashSet<>(persistedBids);
                closedBids.removeAll(refreshedBids);

                for (Bid bid : closedBids)
                    bid.setClosed(true);

                logger.info("bids closed = " + closedBids.size());


                Set<Bid> existingBids = new HashSet<>(persistedBids);
                existingBids.retainAll(refreshedBids);

                for (Bid bid : existingBids) {
                    Set<Snapshot> snapshots = new HashSet<>(snapshotDAO.findByBid(bid));
                    snapshots.add(newSnapshot);
                    bid.setSnapshots(snapshots);
                }

                logger.info("bids already exist = " + existingBids.size());


                Set<Bid> newBids = new HashSet<>(refreshedBids);
                newBids.removeAll(persistedBids);

                for (Bid bid : newBids)
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));

                logger.info("bids new = " + newBids.size());


                bidDAO.createAll(closedBids);
                bidDAO.createAll(existingBids);
                bidDAO.createAll(newBids);
            }
        }
        logger.info("update ended");
    }
}
