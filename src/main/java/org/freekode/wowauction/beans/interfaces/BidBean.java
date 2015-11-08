package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.SnapshotEntity;

import java.util.List;

public interface BidBean {
    List<BidEntity> saveAll(List<BidEntity> bids);

    BidEntity getById(Integer id);

    List<BidEntity> findAll();

    List<BidEntity> closeAll(List<BidEntity> closeBids);

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
