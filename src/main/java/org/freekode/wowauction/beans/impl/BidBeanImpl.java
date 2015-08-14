package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.BidBean;
import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
public class BidBeanImpl implements BidBean {
    @Autowired
    private BidDAO bidDAO;


    @Override
    public Set<Bid> saveAll(Set<Bid> bids) {
        Set<Bid> addedBids = new HashSet<>();
        for (Bid bid : bids) {
            Bid addedItem = bidDAO.save(bid);
            addedBids.add(addedItem);
        }

        return addedBids;
    }

    @Override
    public Bid getById(Integer id) {
        return bidDAO.getById(id);
    }

    @Override
    public List<Bid> findAll() {
        return bidDAO.findAll();
    }

    @Override
    public Bid closeBid(Bid closedBid) {
        Bid bid = bidDAO.getById(closedBid.getId());

        bid.setClosed(true);
        return bidDAO.save(bid);
    }

    @Override
    public List<Bid> findBySnapshot(Snapshot snapshot) {
        return bidDAO.findBySnapshot(snapshot);
    }
}
