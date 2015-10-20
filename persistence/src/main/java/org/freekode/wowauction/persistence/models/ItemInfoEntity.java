package org.freekode.wowauction.persistence.models;

import javax.persistence.*;


@Entity
@Table(name = "items_info")
public class ItemInfoEntity {
    @Id
    private Integer id;

    private String name;

    private String level;

    private String url;

    private String icon;

//    @ManyToOne
//    @JoinColumn(name = "qualityId")
//    private CatalogEntity quality;
//
//    @ManyToOne
//    @JoinColumn(name = "itemClassId")
//    private CatalogEntity itemClass;
//
//    @ManyToOne
//    @JoinColumn(name = "itemSubclassId")
//    private CatalogEntity itemSubclass;
//
//    @ManyToOne
//    @JoinColumn(name = "inventorySlotId")
//    private CatalogEntity inventorySlot;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private ItemEntity item;


    public ItemInfoEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

//    public CatalogEntity getQuality() {
//        return quality;
//    }
//
//    public void setQuality(CatalogEntity quality) {
//        this.quality = quality;
//    }
//
//    public CatalogEntity getItemClass() {
//        return itemClass;
//    }
//
//    public void setItemClass(CatalogEntity itemClass) {
//        this.itemClass = itemClass;
//    }
//
//    public CatalogEntity getItemSubclass() {
//        return itemSubclass;
//    }
//
//    public void setItemSubclass(CatalogEntity itemSubclass) {
//        this.itemSubclass = itemSubclass;
//    }
//
//    public CatalogEntity getInventorySlot() {
//        return inventorySlot;
//    }
//
//    public void setInventorySlot(CatalogEntity inventorySlot) {
//        this.inventorySlot = inventorySlot;
//    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return item.getIdentifier() + "; " + name;
    }
}
