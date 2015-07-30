package org.freekode.wowauction.services;

import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class WorldOfWarcraft {
    public static void parse(Snapshot snapshot) {

        JSONArray bidsJson = (JSONArray) (
                (JSONObject) (
                        (JSONObject) JSONValue.parse(HttpRequest.sendGet(snapshot.getFile()))
                ).get("auctions")
        ).get("auctions");


        Set<Item> items = new HashSet<>();

        for (Object bidObj : bidsJson) {
            JSONObject bidJson = (JSONObject) bidObj;

//            Item item = new Item();
//            item.setId(new Long(bidJson.get("item").toString()));
//
//            Bid bid = new Bid();
//            bid.setId(new Long(bidJson.get("auc").toString()));
//            bid.setBid(new Long(bidJson.get("bid").toString()));
//            bid.setBuyout(new Long(bidJson.get("buyout").toString()));
//            bid.setPlayer(bidJson.get("owner").toString());
//            bid.setQuantity(new Integer(bidJson.get("quantity").toString()));
//            bid.setTimeLeft(Bid.TimeLeft.valueOf(bidJson.get("timeLeft").toString()));
//
//            if (items.contains(item)) {
//                for (Item existedItem : items) {
//                    if (item.equals(existedItem)) {
//                        Set<Bid> bids = existedItem.getBids();
//                        bids.add(bid);
//                        existedItem.setBids(bids);
//                    }
//                }
//            } else {
//                item.setBids(new HashSet<>(Collections.singletonList(bid)));
//                items.add(item);
//            }
        }
    }

    public static Snapshot getSnapshot(Realm realm) {
        Snapshot snapshot = new Snapshot();
        try {
            String url = "http://" + realm.getRegion().toString() + ".battle.net/api/wow/auction/data/" +
                    URLEncoder.encode(realm.getSlug(), "UTF-8").replace("+", "%20");

            JSONArray filesJson = (JSONArray) ((JSONObject) JSONValue.parse(HttpRequest.sendGet(url))).get("files");

            if (filesJson.size() > 1) {
                System.out.println("Not work with more than one auctions");
            }

            String auctionUrl = ((JSONObject) filesJson.get(0)).get("url").toString();
            Date lastModified = new Timestamp(new Long(((JSONObject) filesJson.get(0)).get("lastModified").toString()));

            snapshot.setRealm(realm);
            snapshot.setFile(auctionUrl);
            snapshot.setLastModified(lastModified);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return snapshot;
    }

    public static Set<Realm> getRealms(Realm.Region region) {
        String url = "http://" + region.toString().toLowerCase() + ".battle.net/api/wow/realm/status";

        JSONArray realmsJson = (JSONArray) ((JSONObject) JSONValue.parse(HttpRequest.sendGet(url))).get("realms");

        HashSet<Realm> realms = new HashSet<>();
        for (Object realmObj : realmsJson) {
            JSONObject realmJson = (JSONObject) realmObj;

            Realm newRealm = new Realm();
            newRealm.setRegion(region);
            newRealm.setName(realmJson.get("name").toString());
            newRealm.setSlug(realmJson.get("slug").toString());
            realms.add(newRealm);
        }

        return realms;
    }
}
