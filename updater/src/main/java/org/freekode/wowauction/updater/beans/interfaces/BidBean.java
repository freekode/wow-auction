package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.List;
import java.util.Set;

public interface BidBean {
    List<BidEntity> saveAll(List<BidEntity> bids);

    BidEntity closeBid(BidEntity bid);

    List<BidEntity> findBySnapshot(SnapshotEntity snapshot);
}
