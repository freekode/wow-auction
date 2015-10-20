package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.persistence.models.ItemInfoEntity;

import java.util.Set;

public class ItemInfo extends BaseTransfer<ItemInfoEntity> implements Initializable {
    private Item item;


    public ItemInfo() {
        super();
        entity = new ItemInfoEntity();
    }

    public ItemInfo(ItemInfoEntity entity) {
        super(entity);

        item = new Item(entity.getItem());
    }

    @Override
    public void init(Set options) {
    }

    public Integer getId() {
        return entity.getId();
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

    public Item getItem() {
        return item;
    }

    public enum Options {
    }
}
