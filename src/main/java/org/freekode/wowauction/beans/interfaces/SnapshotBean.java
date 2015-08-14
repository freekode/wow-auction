package org.freekode.wowauction.beans.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;

import java.util.List;

public interface SnapshotBean {
    Snapshot save(Snapshot snapshot);

    Snapshot getById(int id);

    Snapshot getLastByRealm(Realm realm);

    List<Snapshot> findAll();

    List<Snapshot> getByBid(Bid bid);
}
