package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.transfer.Snapshot;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;

    @Override
    public List<Snapshot> findBetweenDates(int realmId, Date startTime, Date endTime, Set options) {
        return snapshotDAO.findBetweenDates(realmId, startTime, endTime, options);
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
    public List<Snapshot> getByBid(BidEntity bid, Set options) {
        return snapshotDAO.findByBid(bid, options);
    }
}
