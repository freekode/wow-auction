package org.freekode.wowauction.controllers.data;

import org.freekode.wowauction.transfer.Bid;
import org.freekode.wowauction.transfer.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemData {
    private Integer id;
    private String identifier;
    private String rand;
    private String seed;
    private String context;
    private List<BidData> closedBids;
    private List<BidData> openBids;
    private ItemInfoData itemInfo;
    private Date createdAt;


    public ItemData() {
    }

    public ItemData(Item item) {
        this.id = item.getId();
        this.identifier = item.getIdentifier();
        this.rand = item.getRand();
        this.seed = item.getSeed();
        this.context = item.getContext();
        this.createdAt = item.getCreatedAt();

        if (item.getClosedBids() != null) {
            closedBids = new ArrayList<>();
            for (Bid bid : item.getClosedBids()) {
                closedBids.add(new BidData(bid));
            }
        }

        if (item.getOpenBids() != null) {
            openBids = new ArrayList<>();
            for (Bid bid : item.getOpenBids()) {
                openBids.add(new BidData(bid));
            }
        }

        if (item.getItemInfo() != null) {
            itemInfo = new ItemInfoData(item.getItemInfo());
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public ItemInfoData getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfoData itemInfo) {
        this.itemInfo = itemInfo;
    }

    public List<BidData> getClosedBids() {
        return closedBids;
    }

    public void setClosedBids(List<BidData> closedBids) {
        this.closedBids = closedBids;
    }

    public List<BidData> getOpenBids() {
        return openBids;
    }

    public void setOpenBids(List<BidData> openBids) {
        this.openBids = openBids;
    }
}
