package org.freekode.wowauction.engine.controllers.data;

import org.freekode.wowauction.engine.transfer.Bid;
import org.freekode.wowauction.engine.transfer.Item;

import java.util.Date;
import java.util.Set;

public class ItemData {
    private Integer id;
    private String identifier;
    private String suffixId;
    private String uniqueId;
    private String context;
    private Set<Bid> bids;
    private ItemInfoData itemInfo;
    private Date createdAt;


    public ItemData() {
    }

    public ItemData(Item item) {
        this.id = item.getId();
        this.identifier = item.getIdentifier();
        this.suffixId = item.getSuffixId();
        this.uniqueId = item.getUniqueId();
        this.context = item.getContext();
        this.createdAt = item.getCreatedAt();

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

    public String getSuffixId() {
        return suffixId;
    }

    public void setSuffixId(String suffixId) {
        this.suffixId = suffixId;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
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
}
