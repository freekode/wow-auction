package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;

public interface SnapshotDAO {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getLast(RealmEntity realm);

    List<SnapshotEntity> findAll();

    List<SnapshotEntity> findByBid(BidEntity bid);
}
