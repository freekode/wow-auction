package org.freekode.wowauction.engine.dao.interfaces;

import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Snapshot;

import java.util.List;

public interface BidDAO {
    Bid save(Bid bid);

    Bid getById(Integer id);

    List<Bid> findAll();

    List<Bid> findBySnapshot(Snapshot snapshot);
}
