package org.freekode.wowauction.services;

import org.apache.logging.log4j.LogManager;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;


@Component
public class SnapshotUpdater {
    private static final Logger logger = LogManager.getLogger(SnapshotUpdater.class);

    @Autowired
    private SnapshotDAO snapshotDAO;

    @Autowired
    private RealmDAO realmDAO;


    @Scheduled(cron = "*/30 * * * * ?")
    public void updateAuction() {
        logger.info("start update");
        for (Realm realm : realmDAO.getAll()) {

            Snapshot newSnapshot = WorldOfWarcraft.getSnapshot(realm);
            Snapshot lastSnapshot = snapshotDAO.getLast(realm);
            logger.info("lastSnapshot", lastSnapshot);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot", newSnapshot);

//                WorldOfWarcraft.parse(newSnapshot);

                snapshotDAO.create(newSnapshot);
            }
        }
    }
}
