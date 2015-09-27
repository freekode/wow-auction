package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.engine.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.engine.transfer.Snapshot;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;

    @Override
    public List<Snapshot> findBetweenDates(Date startTime, Date endTime, Set options) {
        return snapshotDAO.findBetweenDates(startTime, endTime, options);
    }

    @Override
    public List<Snapshot> findByToday(Set options) {
        Date todayStart, todayEnd;
        Calendar today = Calendar.getInstance();

        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        todayStart = today.getTime();

        today.set(Calendar.HOUR_OF_DAY, 23);
        today.set(Calendar.MINUTE, 59);
        today.set(Calendar.SECOND, 59);
        today.set(Calendar.MILLISECOND, 1000);

        todayEnd = today.getTime();


        return findBetweenDates(todayStart, todayEnd, options);
    }

    @Override
    public SnapshotEntity save(SnapshotEntity snapshot) {
        return snapshotDAO.save(snapshot);
    }

    @Override
    public List<Snapshot> findAll(Set options) {
        return snapshotDAO.findAll(options);
    }

    @Override
    public SnapshotEntity getEntity(int id) {
        return snapshotDAO.getEntity(id);
    }

    @Override
    public Snapshot getSnapshot(int id, Set options) {
        return snapshotDAO.getSnapshot(id, options);
    }

    @Override
    public Snapshot getLastByRealm(RealmEntity realm, Set options) {
        return snapshotDAO.getLast(realm, options);
    }

    @Override
    public List<Snapshot> getByBid(BidEntity bid, Set options) {
        return snapshotDAO.findByBid(bid, options);
    }
}
