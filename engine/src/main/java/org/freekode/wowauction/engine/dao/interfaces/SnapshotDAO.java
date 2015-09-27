package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.Date;
import java.util.List;

public interface SnapshotDAO {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getById(int id);

    SnapshotEntity getLast(RealmEntity realm);

    List<SnapshotEntity> findAll();

    List<SnapshotEntity> findByBid(BidEntity bid);

    List<SnapshotEntity> findBetweenDates(Date startTime, Date endTime);
}
