package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.updater.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;


    @Override
    public SnapshotEntity save(SnapshotEntity snapshot) {
        return snapshotDAO.save(snapshot);
    }

    @Override
    public List<SnapshotEntity> findAll() {
        return snapshotDAO.findAll();
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
