package org.freekode.wowauction.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items")
public class ItemEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * main identifier of the item
     */
    private String identifier;

    /**
     * enchant identifier, can ber negative, in json represented as rand
     */
    private String rand;

    /**
     * unique id differentiate between two identical items with different enchantment, represented as seed
     */
    private String seed;

    /**
     * i have no idea
     */
    private String context;

    @OneToMany(mappedBy = "item")
    private Set<BidEntity> bids = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ItemInfoEntity itemInfo;

    @OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
    private ItemCalculationEntity itemCalculation;


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

    public String getSeed() {
        return seed;
    }

    public void setSeed(String seed) {
        this.seed = seed;
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

    public String getRand() {
        return rand;
    }

    public void setRand(String rand) {
        this.rand = rand;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public ItemInfoEntity getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(ItemInfoEntity itemInfo) {
        this.itemInfo = itemInfo;
    }

    public ItemCalculationEntity getItemCalculation() {
        return itemCalculation;
    }

    public void setItemCalculation(ItemCalculationEntity itemCalculation) {
        this.itemCalculation = itemCalculation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemEntity item = (ItemEntity) o;

        if (!identifier.equals(item.identifier)) return false;
        return seed.equals(item.seed);

    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + seed.hashCode();
        return result;
    }
}
