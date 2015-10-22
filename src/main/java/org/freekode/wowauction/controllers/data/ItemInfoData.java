package org.freekode.wowauction.controllers.data;

import org.freekode.wowauction.transfer.ItemInfo;

public class ItemInfoData {
    private String name;
    private String level;
    private String url;
    private String icon;


    public ItemInfoData() {
    }

    public ItemInfoData(ItemInfo itemInfo) {
        name = itemInfo.getName();
        level = itemInfo.getLevel();
        url = itemInfo.getUrl();
        icon = itemInfo.getIcon();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
}
