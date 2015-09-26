package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Snapshot;

import java.util.List;
import java.util.Set;

public interface BidBean {
    Set<Bid> saveAll(Set<Bid> bids);

    Bid closeBid(Bid bid);

    List<Bid> findBySnapshot(Snapshot snapshot);
}
