package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.ItemInfoEntity;

import java.util.Set;

public class ItemInfo extends BaseTransfer<ItemInfoEntity> implements Initializable {
    private CatalogEntry quality;

    private CatalogEntry itemClass;

    private CatalogEntry itemSubclass;

    private CatalogEntry inventorySlot;


    public ItemInfo() {
        super();
        entity = new ItemInfoEntity();
    }

    public ItemInfo(ItemInfoEntity entity) {
        super(entity);

        if (entity.getQuality() != null) {
            quality = new CatalogEntry(entity.getQuality());
        }

        if (entity.getItemClass() != null) {
            itemClass = new CatalogEntry(entity.getItemClass());
        }

        if (entity.getItemSubclass() != null) {
            itemSubclass = new CatalogEntry(entity.getItemSubclass());
        }

        if (entity.getInventorySlot() != null) {
            inventorySlot = new CatalogEntry(entity.getInventorySlot());
        }
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

    public CatalogEntry getQuality() {
        return quality;
    }

    public void setQuality(CatalogEntry quality) {
        this.quality = quality;
    }

    public CatalogEntry getItemClass() {
        return itemClass;
    }

    public void setItemClass(CatalogEntry itemClass) {
        this.itemClass = itemClass;
    }

    public CatalogEntry getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(CatalogEntry itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public CatalogEntry getInventorySlot() {
        return inventorySlot;
    }

    public void setInventorySlot(CatalogEntry inventorySlot) {
        this.inventorySlot = inventorySlot;
    }

    public enum Options {
    }
}
