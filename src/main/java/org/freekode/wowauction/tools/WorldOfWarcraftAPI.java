package org.freekode.wowauction.tools;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldOfWarcraftAPI {
    public static List<Map<String, String>> getAuctions(String url) {
        JSONObject fullJson = (JSONObject) JSONValue.parse(HttpRequest.sendGet(url));
        JSONArray bidsJson = (JSONArray) fullJson.get("auctions");


        List<Map<String, String>> auctions = new ArrayList<>();

        for (Object bidObj : bidsJson) {
            JSONObject bidJson = (JSONObject) bidObj;

            Map<String, String> auction = new HashMap<>();
            auction.put("auc", bidJson.get("auc").toString());
            auction.put("item", bidJson.get("item").toString());
            auction.put("owner", bidJson.get("owner").toString());
            auction.put("ownerRealm", bidJson.get("ownerRealm").toString());
            auction.put("bid", bidJson.get("bid").toString());
            auction.put("buyout", bidJson.get("buyout").toString());
            auction.put("quantity", bidJson.get("quantity").toString());
            auction.put("timeLeft", bidJson.get("timeLeft").toString());
            auction.put("rand", bidJson.get("rand").toString());
            auction.put("seed", bidJson.get("seed").toString());
            auction.put("context", bidJson.get("context").toString());
            auctions.add(auction);
        }

        return auctions;
    }

    public static Map<String, String> getSnapshot(String region, String name, String apiKey) {
        Map<String, String> values = new HashMap<>();
        try {
            String url = "https://" + region + ".api.battle.net/wow/auction/data/" +
                    URLEncoder.encode(name, "UTF-8").replace("+", "%20") + "?apikey=" + apiKey;

            JSONArray filesJson = (JSONArray) ((JSONObject) JSONValue.parse(HttpRequest.sendGet(url))).get("files");

            if (filesJson.size() > 1) {
                System.out.println("Not work with more than one auctions");
            }

            String auctionUrl = ((JSONObject) filesJson.get(0)).get("url").toString();
            String lastModified = ((JSONObject) filesJson.get(0)).get("lastModified").toString();

            values.put("url", auctionUrl);
            values.put("lastModified", lastModified);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return values;
    }

    public static List<Map<String, String>> getRealms(String region) {
        String url = "https://" + region + ".battle.net/api/wow/realm/status";

        JSONArray realmsJson = (JSONArray) ((JSONObject) JSONValue.parse(HttpRequest.sendGet(url))).get("realms");

        List<Map<String, String>> realms = new ArrayList<>();
        for (Object realmObj : realmsJson) {
            JSONObject realmJson = (JSONObject) realmObj;

            Map<String, String> realm = new HashMap<>();
            realm.put("region", region);
            realm.put("name", realmJson.get("name").toString());
            realm.put("slug", realmJson.get("slug").toString());
            realms.add(realm);
        }

        return realms;
    }

    public static Map<String, Object> getItemInfo(String region, String itemId) {
        return null;
    }
}
