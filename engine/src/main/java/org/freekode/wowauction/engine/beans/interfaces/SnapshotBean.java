package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.Date;
import java.util.List;

public interface SnapshotBean {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getById(int id);

    SnapshotEntity getLastByRealm(RealmEntity realm);

    List<SnapshotEntity> findAll();

    List<SnapshotEntity> findBetweenDates(Date startTime, Date endTime);

    List<SnapshotEntity> findByToday();

    List<SnapshotEntity> getByBid(BidEntity bid);
}
