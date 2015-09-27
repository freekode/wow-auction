package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.engine.transfer.Snapshot;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface SnapshotBean {
    SnapshotEntity save(SnapshotEntity snapshot);

    SnapshotEntity getEntity(int id);

    Snapshot getSnapshot(int id, Set options);

    List<Snapshot> findAll(Set options);

    List<Snapshot> findBetweenDates(int realmId, Date startTime, Date endTime, Set options);

    List<Snapshot> getByBid(BidEntity bid, Set options);
}
