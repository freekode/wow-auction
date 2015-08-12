package org.freekode.wowauction.beans.interfaces;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Item;

import java.util.Set;

public interface BidBean {
    Bid closeBid(Bid bid);
}
