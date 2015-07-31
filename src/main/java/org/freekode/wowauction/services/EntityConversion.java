package org.freekode.wowauction.services;

import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Item;

import java.math.BigInteger;
import java.util.*;

public class EntityConversion {
    public static Set<Bid> convertToBids(List<Map<String, String>> auctionList) {
        Set<Bid> bids = new HashSet<>();

        for (Map<String, String> auctionMap : auctionList) {
            Item item = new Item();
            item.setIdentifier(new Integer(auctionMap.get("item")));
            item.setUniqueId(new BigInteger(auctionMap.get("seed")));
            item.setSuffixId(new Integer(auctionMap.get("rand")));
            item.setContext(new Integer(auctionMap.get("context")));

            Bid bid = new Bid();
            bid.setId(new Long(auctionMap.get("auc")));
            bid.setRate(new BigInteger(auctionMap.get("bid")));
            bid.setBuyout(new BigInteger(auctionMap.get("buyout")));
            bid.setQuantity(new Integer(auctionMap.get("quantity")));
            bid.setTimeLeft(Bid.TimeLeft.valueOf(auctionMap.get("timeLeft")));
            bid.setItem(item);

            bids.add(bid);
        }

        return bids;
    }
}
