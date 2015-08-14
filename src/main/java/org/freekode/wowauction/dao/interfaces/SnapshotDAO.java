package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface SnapshotDAO {
    Snapshot save(Snapshot snapshot);

    Snapshot getById(int id);

    Snapshot getLast(Realm realm);

    List<Snapshot> findAll();

    List<Snapshot> findByBid(Bid bid);

    List<Snapshot> findByToday();
}
