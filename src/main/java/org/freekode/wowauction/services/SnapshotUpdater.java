package org.freekode.wowauction.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;


@Component
public class SnapshotUpdater {
    private static final Logger logger = LogManager.getLogger(SnapshotUpdater.class);

    @Autowired
    private SnapshotDAO snapshotDAO;

    @Autowired
    private RealmDAO realmDAO;


    @Scheduled(cron = "0 */3 * * * ?")
    public void updateAuction() {
        logger.info("start update");
        for (Realm realm : realmDAO.getAll()) {

            Map<String, String> newSnapshotMap = WorldOfWarcraftAPI.getSnapshot(realm.getRegion().toString(), realm.getSlug());
            Snapshot newSnapshot = new Snapshot();
            newSnapshot.setRealm(realm);
            newSnapshot.setFile(newSnapshotMap.get("url"));
            newSnapshot.setLastModified(new Date(new Long(newSnapshotMap.get("lastModified"))));


            Snapshot lastSnapshot = snapshotDAO.getLast(realm);
            logger.info("lastSnapshot = " + lastSnapshot);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                logger.info("newSnapshot = " + newSnapshot);
                snapshotDAO.create(newSnapshot);

                List<Map<String, String>> items = WorldOfWarcraftAPI.parse(newSnapshot.getFile());

                logger.info("items size = " + items.size());
            }
        }
    }
}
