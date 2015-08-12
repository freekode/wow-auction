package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.BidBean;
import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.models.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BidBeanImpl implements BidBean {
    @Autowired
    private BidDAO bidDAO;


    @Override
    public Bid closeBid(Bid closedBid) {
        Bid bid = bidDAO.getById(closedBid.getId());

        bid.setClosed(true);
        return bidDAO.update(bid);
    }
}
