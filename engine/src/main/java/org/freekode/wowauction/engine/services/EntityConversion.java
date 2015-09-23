package org.freekode.wowauction.engine.services;


import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Item;

import java.math.BigInteger;
import java.util.*;

public class EntityConversion {
    public static Set<Bid> convertToBids(List<Map<String, String>> auctionList) {
        Set<Bid> bids = new HashSet<>();

        for (Map<String, String> auctionMap : auctionList) {
            Item item = new Item();
            item.setIdentifier(auctionMap.get("item"));
            item.setUniqueId(auctionMap.get("seed"));
            item.setSuffixId(auctionMap.get("rand"));
            item.setContext(auctionMap.get("context"));

            Bid bid = new Bid();
            bid.setIdentifier(auctionMap.get("auc"));
            bid.setRate(new BigInteger(auctionMap.get("bid")));
            bid.setBuyout(new BigInteger(auctionMap.get("buyout")));
            bid.setQuantity(new Integer(auctionMap.get("quantity")));
            bid.setTimeLeft(Bid.TimeLeft.valueOf(auctionMap.get("timeLeft")));
            bid.setItem(item);

            bids.add(bid);
        }

        return bids;
    }

    // TODO try to use that method
    public static Set<Item> inverseBidsToItems(Set<Bid> bids) {
        Set<Item> items = new HashSet<>();
        for (Bid bid : bids) {
            Item item = bid.getItem();

            if (items.contains(item)) {
                for (Item existedItem : items) {
                    if (existedItem.equals(item)) {
                        Set<Bid> itemBids = existedItem.getBids();
                        itemBids.add(bid);
                        existedItem.setBids(itemBids);
                    }
                }
            } else {
                item.setBids(new HashSet<>(Collections.singletonList(bid)));
                items.add(item);
            }
        }

        return items;
    }

    public static Set<Item> getItemsWithoutBids(Set<Bid> bids) {
        Set<Item> items = new HashSet<>();
        for (Bid bid : bids) {
            Item item = bid.getItem();

            items.add(item);
        }

        return items;
    }
}
