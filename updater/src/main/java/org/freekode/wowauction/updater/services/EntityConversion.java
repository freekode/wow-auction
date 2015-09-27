package org.freekode.wowauction.updater.services;


import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.ItemEntity;

import java.math.BigInteger;
import java.util.*;

public class EntityConversion {
    public static Set<BidEntity> convertToBids(List<Map<String, String>> auctionList) {
        Set<BidEntity> bids = new HashSet<>();

        for (Map<String, String> auctionMap : auctionList) {
            ItemEntity item = new ItemEntity();
            item.setIdentifier(auctionMap.get("item"));
            item.setUniqueId(auctionMap.get("seed"));
            item.setSuffixId(auctionMap.get("rand"));
            item.setContext(auctionMap.get("context"));

            BidEntity bid = new BidEntity();
            bid.setIdentifier(auctionMap.get("auc"));
            bid.setRate(new BigInteger(auctionMap.get("bid")));
            bid.setBuyout(new BigInteger(auctionMap.get("buyout")));
            bid.setQuantity(new Integer(auctionMap.get("quantity")));
            bid.setTimeLeft(BidEntity.TimeLeft.valueOf(auctionMap.get("timeLeft")));
            bid.setItem(item);

            bids.add(bid);
        }

        return bids;
    }

    // TODO try to use that method
    public static Set<ItemEntity> inverseBidsToItems(Set<BidEntity> bids) {
        Set<ItemEntity> items = new HashSet<>();
        for (BidEntity bid : bids) {
            ItemEntity item = bid.getItem();

            if (items.contains(item)) {
                for (ItemEntity existedItem : items) {
                    if (existedItem.equals(item)) {
                        Set<BidEntity> itemBids = existedItem.getBids();
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

    public static Set<ItemEntity> getItemsWithoutBids(Set<BidEntity> bids) {
        Set<ItemEntity> items = new HashSet<>();
        for (BidEntity bid : bids) {
            ItemEntity item = bid.getItem();

            items.add(item);
        }

        return items;
    }
}
