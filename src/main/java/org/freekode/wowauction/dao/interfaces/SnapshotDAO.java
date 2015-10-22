package org.freekode.wowauction.dao.interfaces;


import org.freekode.wowauction.transfer.Snapshot;
import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.RealmEntity;
import org.freekode.wowauction.models.SnapshotEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SnapshotDAO {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getEntity(int id);

    Snapshot getSnapshot(int id, Set options);

    SnapshotEntity getLastEntity(RealmEntity realm);

    List<Snapshot> findAll(Set options);

    List<SnapshotEntity> findByBid(BidEntity bid);

    List<Snapshot> findBetweenDates(int realmId, Date startTime, Date endTime, Set options);
}
