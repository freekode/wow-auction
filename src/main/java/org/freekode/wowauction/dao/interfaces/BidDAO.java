package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;

import java.util.List;
import java.util.Set;

public interface BidDAO {
    void create(Bid bid);

    Bid getById(Integer id);

    List<Bid> getAll();

    List<Bid> getAllByRealm(Realm realm);

}
