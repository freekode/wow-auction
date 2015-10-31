package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.BidBean;
import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.SnapshotEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BidBeanImpl implements BidBean {
    @Autowired
    private BidDAO bidDAO;


    @Override
    public List<BidEntity> saveAll(List<BidEntity> bids) {
        List<BidEntity> savedBids = new ArrayList<>();
        for (BidEntity bid : bids) {
            BidEntity addedItem = bidDAO.save(bid);
            savedBids.add(addedItem);
        }

        return savedBids;
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
    public List<BidEntity> closeAll(List<BidEntity> closeBids) {
        for (BidEntity bid : closeBids) {
            bid.setClosed(true);
        }

        return saveAll(closeBids);
    }

    @Override
    public List<BidEntity> findBySnapshot(SnapshotEntity snapshot) {
        return bidDAO.findBySnapshot(snapshot);
    }
}
