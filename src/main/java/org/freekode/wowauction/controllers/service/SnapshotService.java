package org.freekode.wowauction.controllers.service;

import org.freekode.wowauction.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.controllers.data.SnapshotData;
import org.freekode.wowauction.transfer.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class SnapshotService {
    @Autowired
    private SnapshotBean snapshotBean;


    public List<SnapshotData> get24h(Integer realmId) {
        Date todayStart, todayEnd;
        Calendar today = Calendar.getInstance();

        todayEnd = today.getTime();

        today.add(Calendar.DAY_OF_MONTH, -1);
        todayStart = today.getTime();

        List<SnapshotData> snapshots = new ArrayList<>();
        for (Snapshot snapshot : snapshotBean.findBetweenDates(realmId, todayStart, todayEnd, null)) {
            snapshots.add(new SnapshotData(snapshot));
        }

        return snapshots;
    }

    public List<SnapshotData> findByRealm(Integer realmId) {
        List<SnapshotData> snapshots = new ArrayList<>();
        for (Snapshot snapshot : snapshotBean.find(realmId, null)) {
            snapshots.add(new SnapshotData(snapshot));
        }

        return snapshots;
    }
}
