package org.freekode.wowauction.persistence.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items", indexes = {@Index(columnList = "identifier,uniqueId", name = "items_unique_identity", unique = true)})
public class ItemEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * unique identifier of the item
     */
    private String identifier;

    /**
     * enchant identifier, can ber negative, in json represented as rand
     */
    private String suffixId;

    /**
     * unique id differentiate between two identical items with different enchantment, represented as seed
     */
    private String uniqueId;

    /**
     * i have no idea
     */
    private String context;

    @OneToMany(mappedBy = "item")
    private Set<BidEntity> bids = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne
    private ItemInfoEntity itemInfo;


    public ItemEntity() {
    }

    @PrePersist
    protected void onCreate() {
        setCreatedAt(new Date());
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<BidEntity> getBids() {
        return bids;
    }

    public void setBids(Set<BidEntity> bids) {
        this.bids = bids;
    }

    public String getSuffixId() {
        return suffixId;
    }

    public void setSuffixId(String suffixId) {
        this.suffixId = suffixId;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity item = (ItemEntity) o;

        if (!identifier.equals(item.identifier)) return false;
        return uniqueId.equals(item.uniqueId);

    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + uniqueId.hashCode();
        return result;
    }

    public ItemInfoEntity getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfoEntity itemInfo) {
        this.itemInfo = itemInfo;
    }
}
