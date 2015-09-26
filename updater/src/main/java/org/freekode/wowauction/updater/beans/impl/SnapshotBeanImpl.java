package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.updater.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Realm;
import org.freekode.wowauction.persistence.models.Snapshot;
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
    public Snapshot save(Snapshot snapshot) {
        return snapshotDAO.save(snapshot);
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
