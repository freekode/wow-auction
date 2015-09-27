package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.engine.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;

    @Override
    public List<SnapshotEntity> findBetweenDates(Date startTime, Date endTime) {
        return snapshotDAO.findBetweenDates(startTime, endTime);
    }

    @Override
    public List<SnapshotEntity> findByToday() {
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
    public SnapshotEntity save(SnapshotEntity snapshot) {
        return snapshotDAO.save(snapshot);
    }

    @Override
    public List<SnapshotEntity> findAll() {
        return snapshotDAO.findAll();
    }

    @Override
    public SnapshotEntity getById(int id) {
        return snapshotDAO.getById(id);
    }

    @Override
    public SnapshotEntity getLastByRealm(RealmEntity realm) {
        return snapshotDAO.getLast(realm);
    }

    @Override
    public List<SnapshotEntity> getByBid(BidEntity bid) {
        return snapshotDAO.findByBid(bid);
    }
}
