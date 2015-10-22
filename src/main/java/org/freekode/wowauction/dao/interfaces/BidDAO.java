package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.SnapshotEntity;

import java.util.List;

public interface BidDAO {
    List<BidEntity> saveAll(List<BidEntity> bids);

    List<BidEntity> closeAll(List<BidEntity> bids);

    BidEntity save(BidEntity bid);

    BidEntity getById(Integer id);

    List<BidEntity> findAll();

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
