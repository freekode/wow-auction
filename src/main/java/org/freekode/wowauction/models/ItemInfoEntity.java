package org.freekode.wowauction.models;

import javax.persistence.*;


@Entity
@Table(name = "items_info")
public class ItemInfoEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer itemLevel;

    private String icon;

    private Long sellPrice;

    @ManyToOne
    @JoinColumn(name = "qualityId")
    private CatalogEntryEntity quality;

    @ManyToOne
    @JoinColumn(name = "itemClassId")
    private CatalogEntryEntity itemClass;

    @ManyToOne
    @JoinColumn(name = "itemSubclassId")
    private CatalogEntryEntity itemSubclass;

    @ManyToOne
    @JoinColumn(name = "inventorySlotId")
    private CatalogEntryEntity inventorySlot;

    @OneToOne
    @JoinColumn(name = "itemId", referencedColumnName = "id")
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

    public void setName(String name) {
        this.name = name;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public CatalogEntryEntity getQuality() {
        return quality;
    }

    public void setQuality(CatalogEntryEntity quality) {
        this.quality = quality;
    }

    public CatalogEntryEntity getItemClass() {
        return itemClass;
    }

    public void setItemClass(CatalogEntryEntity itemClass) {
        this.itemClass = itemClass;
    }

    public CatalogEntryEntity getItemSubclass() {
        return itemSubclass;
    }

    public void setItemSubclass(CatalogEntryEntity itemSubclass) {
        this.itemSubclass = itemSubclass;
    }

    public CatalogEntryEntity getInventorySlot() {
        return inventorySlot;
    }

    public void setInventorySlot(CatalogEntryEntity inventorySlot) {
        this.inventorySlot = inventorySlot;
    }

    public Integer getItemLevel() {
        return itemLevel;
    }

    public void setItemLevel(Integer itemLevel) {
        this.itemLevel = itemLevel;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return item.getIdentifier() + "; " + name;
    }

    public Long getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Long sellPrice) {
        this.sellPrice = sellPrice;
    }
}
