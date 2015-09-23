package org.freekode.wowauction.engine.beans.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Snapshot;

import java.util.List;
import java.util.Set;

public interface BidBean {
    Set<Bid> saveAll(Set<Bid> bids);

    Bid getById(Integer id);

    List<Bid> findAll();

    Bid closeBid(Bid bid);

    List<Bid> findBySnapshot(Snapshot snapshot);
}
