package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.ItemInfoEntity;

import java.util.Set;

public class ItemInfo extends BaseTransfer<ItemInfoEntity> implements Initializable {
    public ItemInfo() {
        super();
        entity = new ItemInfoEntity();
    }

    public ItemInfo(ItemInfoEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getName() {
        return entity.getName();
    }

    public void setName(String name) {
        entity.setName(name);
    }

    public String getLevel() {
        return entity.getLevel();
    }

    public void setLevel(String level) {
        entity.setLevel(level);
    }

    public String getIcon() {
        return entity.getIcon();
    }

    public void setIcon(String icon) {
        entity.setIcon(icon);
    }

    public String getUrl() {
        return entity.getUrl();
    }

    public void setUrl(String url) {
        entity.setUrl(url);
    }

    public enum Options {
    }
}
