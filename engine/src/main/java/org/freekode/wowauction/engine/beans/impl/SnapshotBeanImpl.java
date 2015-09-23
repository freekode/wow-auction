package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.engine.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Transactional
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;

    @Override
    public List<Snapshot> findBetweenDates(Date startTime, Date endTime) {
        return snapshotDAO.findBetweenDates(startTime, endTime);
    }

    @Override
    public List<Snapshot> findByToday() {
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


        return findBetweenDates(todayStart, todayEnd);
    }

    @Override
    public Snapshot save(Snapshot snapshot) {
        return snapshotDAO.save(snapshot);
    }

    @Override
    public List<Snapshot> findAll() {
        return snapshotDAO.findAll();
    }

    @Override
    public Snapshot getById(int id) {
        return snapshotDAO.getById(id);
    }

    @Override
    public Snapshot getLastByRealm(Realm realm) {
        return snapshotDAO.getLast(realm);
    }

    @Override
    public List<Snapshot> getByBid(Bid bid) {
        return snapshotDAO.findByBid(bid);
    }
}
