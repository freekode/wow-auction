package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.BidBean;
import org.freekode.wowauction.engine.dao.interfaces.BidDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BidBeanImpl implements BidBean {
    @Autowired
    private BidDAO bidDAO;


    @Override
    public Set<BidEntity> saveAll(Set<BidEntity> bids) {
        Set<BidEntity> addedBids = new HashSet<>();
        for (BidEntity bid : bids) {
            BidEntity addedItem = bidDAO.save(bid);
            addedBids.add(addedItem);
        }

        return addedBids;
    }

    @Override
    public BidEntity getById(Integer id) {
        return bidDAO.getById(id);
    }

    @Override
    public List<BidEntity> findAll() {
        return bidDAO.findAll();
    }

    @Override
    public BidEntity closeBid(BidEntity closedBid) {
        BidEntity bid = bidDAO.getById(closedBid.getId());

        bid.setClosed(true);
        return bidDAO.save(bid);
    }

    @Override
    public List<BidEntity> findBySnapshot(SnapshotEntity snapshot) {
        return bidDAO.findBySnapshot(snapshot);
    }
}
