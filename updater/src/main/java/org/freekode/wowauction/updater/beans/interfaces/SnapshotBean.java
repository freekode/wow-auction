package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;

public interface SnapshotBean {
    SnapshotEntity save(SnapshotEntity snapshot);

    List<SnapshotEntity> findAll();

    SnapshotEntity getLastByRealm(RealmEntity realm);

    List<SnapshotEntity> getByBid(BidEntity bid);
}
