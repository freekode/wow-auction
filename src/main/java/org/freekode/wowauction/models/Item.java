package org.freekode.wowauction.models;

import javax.persistence.*;
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

    private String identifier;

    @ManyToOne
    @JoinColumn(name = "snapshotId")
    private Snapshot snapshot;

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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Snapshot getSnapshot() {
        return snapshot;
    }

    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
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
}
