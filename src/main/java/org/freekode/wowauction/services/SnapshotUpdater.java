package org.freekode.wowauction.services;

import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SnapshotUpdater {
    @Autowired
    private SnapshotDAO snapshotDAO;

    @Autowired
    private RealmDAO realmDAO;


    @Scheduled(cron = "* */5 * * * ?")
    public void updateAuction() {
        for (Realm realm : realmDAO.getAll()) {
            Snapshot newSnapshot = WorldOfWarcraft.getSnapshot(realm);
            Snapshot lastSnapshot = snapshotDAO.getLast(realm);

            if (lastSnapshot == null || lastSnapshot.getLastModified().getTime() < newSnapshot.getLastModified().getTime()) {
                System.out.println("nya");

                WorldOfWarcraft.parse(newSnapshot);

                snapshotDAO.create(newSnapshot);
            }
        }
    }
}
