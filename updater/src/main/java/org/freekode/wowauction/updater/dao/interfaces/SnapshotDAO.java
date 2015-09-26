package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Realm;
import org.freekode.wowauction.persistence.models.Snapshot;

import java.util.Date;
import java.util.List;

public interface SnapshotDAO {
    Snapshot save(Snapshot snapshot);

    Snapshot getLast(Realm realm);

    List<Snapshot> findByBid(Bid bid);
}
