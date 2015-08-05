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


    @Scheduled(cron = "0 */10 * * * ?")
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
                logger.info("newSnapshot = " + newSnapshot);
                snapshotDAO.create(newSnapshot);

                List<Map<String, String>> auctionList = WorldOfWarcraftAPI.getAuctions(newSnapshot.getFile());
                logger.info("auctionList size = " + auctionList.size());

                Set<Bid> newBids = EntityConversion.convertToBids(auctionList);
                for (Bid bid : newBids) {
                    bid.setSnapshots(new HashSet<>(Collections.singletonList(newSnapshot)));
                }

                Set<Bid> oldBids = new HashSet<>(bidDAO.findBySnapshot(lastSnapshot));
                Set<Bid> oldBidsOriginal = new HashSet<>(oldBids);


                oldBids.removeAll(newBids);
                logger.info("old closed bids = " + oldBids.size());

                oldBidsOriginal.retainAll(newBids);
                logger.info("old closed bids = " + oldBidsOriginal.size());


                bidDAO.createAll(newBids);
            }
        }
        logger.info("update ended");
    }
}
