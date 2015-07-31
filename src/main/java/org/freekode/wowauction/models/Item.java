package org.freekode.wowauction.models;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items")
public class Item {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * unique identifier of the item
     */
    private Integer identifier;

    /**
     * enchant identifier, can ber negative, in json represented as rand
     */
    private Integer suffixId;

    /**
     * unique id differentiate between two unique items with different enchantment, represented as seed
     */
    private BigInteger uniqueId;

    /**
     * i have no idea
     */
    private Integer context;

    @OneToMany(mappedBy = "item")
    private Set<Bid> bids = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Item() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
        this.bids = bids;
    }

    public Integer getSuffixId() {
        return suffixId;
    }

    public void setSuffixId(Integer suffixId) {
        this.suffixId = suffixId;
    }

    public BigInteger getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(BigInteger uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getContext() {
        return context;
    }

    public void setContext(Integer context) {
        this.context = context;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!identifier.equals(item.identifier)) return false;
        return uniqueId.equals(item.uniqueId);

    }

    @Override
    public int hashCode() {
        int result = identifier.hashCode();
        result = 31 * result + uniqueId.hashCode();
        return result;
    }
}
