package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;
import java.util.Set;

public interface BidBean {
    Set<BidEntity> saveAll(Set<BidEntity> bids);

    BidEntity getById(Integer id);

    List<BidEntity> findAll();

    BidEntity closeBid(BidEntity bid);

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
