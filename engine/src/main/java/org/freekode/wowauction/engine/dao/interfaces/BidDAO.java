package org.freekode.wowauction.engine.dao.interfaces;

import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;

public interface BidDAO {
    BidEntity save(BidEntity bid);

    BidEntity getById(Integer id);

    List<BidEntity> findAll();

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
