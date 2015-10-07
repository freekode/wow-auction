package org.freekode.wowauction.persistence.models;

import javax.persistence.*;


@Entity
@Table(name = "items_info", indexes = {@Index(columnList = "identifier,uniqueId", name = "items_unique_identity", unique = true)})
public class ItemInfoEntity {
    @Id
    private Integer id;

    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }
}
