package org.freekode.wowauction.tools;


import org.freekode.wowauction.models.BidEntity;
import org.freekode.wowauction.models.ItemEntity;

import java.util.*;

public class EntityConversion {
    public static List<BidEntity> convertToBids(List<Map<String, String>> auctionList) {
        List<BidEntity> bids = new ArrayList<>();

        for (Map<String, String> auctionMap : auctionList) {
            ItemEntity item = new ItemEntity();
            item.setIdentifier(auctionMap.get("item"));
            item.setSeed(auctionMap.get("seed"));
            item.setRand(auctionMap.get("rand"));
            item.setContext(auctionMap.get("context"));

            BidEntity bid = new BidEntity();
            bid.setIdentifier(auctionMap.get("auc"));
            bid.setRate(new Long(auctionMap.get("bid")));
            bid.setBuyout(new Long(auctionMap.get("buyout")));
            bid.setQuantity(new Integer(auctionMap.get("quantity")));
            bid.setTimeLeft(BidEntity.TimeLeft.valueOf(auctionMap.get("timeLeft")));
            bid.setItem(item);

            bids.add(bid);
        }

        return bids;
    }

    // TODO try to use that method
    public static List<ItemEntity> inverseBidsToItems(List<BidEntity> bids) {
        List<ItemEntity> items = new ArrayList<>();
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

    public static List<ItemEntity> getItemsWithoutBids(List<BidEntity> bids) {
        List<ItemEntity> items = new ArrayList<>();
        for (BidEntity bid : bids) {
            ItemEntity item = bid.getItem();

            if (!items.contains(item)) {
                items.add(item);
            }
        }

        return items;
    }
}
