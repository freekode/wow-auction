package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.engine.transfer.Snapshot;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SnapshotDAO {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getEntity(int id);

    Snapshot getSnapshot(int id, Set options);

    Snapshot getLast(RealmEntity realm, Set options);

    List<Snapshot> findAll(Set options);

    List<Snapshot> findByBid(BidEntity bid, Set options);

    List<Snapshot> findBetweenDates(int realmId, Date startTime, Date endTime, Set options);
}
