package org.freekode.wowauction.persistence.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "items", indexes = {@Index(columnList = "identifier,uniqueId", name = "items_unique_identity", unique = true)})
public class Item {
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
    private Set<Bid> bids = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Item() {
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

    public Set<Bid> getBids() {
        return bids;
    }

    public void setBids(Set<Bid> bids) {
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
