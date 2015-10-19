package org.freekode.wowauction.updater.dao.interfaces;

import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;

public interface BidDAO {
    List<BidEntity> saveAll(List<BidEntity> bids);

    List<BidEntity> closeAll(List<BidEntity> bids);

    BidEntity save(BidEntity bid);

    BidEntity getById(Integer id);

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
