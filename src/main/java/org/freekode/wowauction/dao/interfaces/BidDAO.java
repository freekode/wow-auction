package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Snapshot;

import java.util.List;
import java.util.Set;

public interface BidDAO {
    void create(Bid bid);

    void createAll(Set<Bid> bids);

    Bid getById(Long id);

    List<Bid> findAll();

    List<Bid> findBySnapshot(Snapshot snapshot);
}
