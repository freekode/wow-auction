package org.freekode.wowauction.persistence.models;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "snapshots")
public class SnapshotEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String file;

    private Date lastModified;

    private Integer closed;

    private Integer existing;

    private Integer newAmount;

    @ManyToOne
    @JoinColumn(name = "realmId")
    private RealmEntity realm;

    @ManyToMany(mappedBy = "snapshots")
    private Set<BidEntity> bids = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public SnapshotEntity() {
    }

    @PrePersist
    protected void onCreate() {
        setCreatedAt(new Date());
    }

    public Integer getExisting() {
        return existing;
    }

    public void setExisting(Integer existing) {
        this.existing = existing;
    }

    public Integer getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Integer newAmount) {
        this.newAmount = newAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public RealmEntity getRealm() {
        return realm;
    }

    public void setRealm(RealmEntity realm) {
        this.realm = realm;
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

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    @Override
    public String toString() {
        return realm.getName() + "; " + lastModified;
    }
}
