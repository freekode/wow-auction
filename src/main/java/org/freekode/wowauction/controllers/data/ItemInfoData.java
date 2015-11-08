package org.freekode.wowauction.controllers.data;

import org.freekode.wowauction.transfer.ItemInfo;

public class ItemInfoData {
    private String name;
    private Integer itemLevel;
    private String url;
    private String icon;
    private CatalogEntryData quality;
    private CatalogEntryData itemClass;
    private CatalogEntryData itemSubclass;
    private CatalogEntryData inventorySlot;


    public ItemInfoData() {
    }

    public ItemInfoData(ItemInfo itemInfo) {
        name = itemInfo.getName();
        itemLevel = itemInfo.getItemLevel();
        url = itemInfo.getUrl();
        icon = itemInfo.getIcon();

        if (itemInfo.getQuality() != null) {
            quality = new CatalogEntryData(itemInfo.getQuality());
        }

        if (itemInfo.getItemClass() != null) {
            itemClass = new CatalogEntryData(itemInfo.getItemClass());
        }

        if (itemInfo.getItemSubclass() != null) {
            itemSubclass = new CatalogEntryData(itemInfo.getItemSubclass());
        }

        if (itemInfo.getInventorySlot() != null) {
            inventorySlot = new CatalogEntryData(itemInfo.getInventorySlot());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CatalogEntryData getQuality() {
        return quality;
    }

    public void setQuality(CatalogEntryData quality) {
        this.quality = quality;
    }

    public CatalogEntryData getItemClass() {
        return itemClass;
    }

    public void setItemClass(CatalogEntryData itemClass) {
        this.itemClass = itemClass;
    }

    public CatalogEntryData getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(CatalogEntryData itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public CatalogEntryData getInventorySlot() {
        return inventorySlot;
    }

    public void setInventorySlot(CatalogEntryData inventorySlot) {
        this.inventorySlot = inventorySlot;
    }
}
