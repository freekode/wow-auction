package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class SnapshotBeanImpl implements SnapshotBean {
    @Autowired
    private SnapshotDAO snapshotDAO;


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
