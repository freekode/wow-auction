package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;

import java.util.List;

public interface SnapshotDAO {
    void create(Snapshot snapshot);

    Snapshot getById(Integer id);

    Snapshot getLast(Realm realm);

    List<Snapshot> findAll();

    List<Snapshot> findByBid(Bid bid);
}
