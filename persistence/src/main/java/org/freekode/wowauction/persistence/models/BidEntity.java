package org.freekode.wowauction.persistence.models;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bids")
public class BidEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    private Long rate;

    private Long buyout;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TimeLeft timeLeft;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private ItemEntity item;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private PlayerEntity player;

    @ManyToMany
    @JoinTable(name = "snapshot_bid",
            joinColumns = {@JoinColumn(name = "bidId")},
            inverseJoinColumns = {@JoinColumn(name = "snapshotId")})
    private Set<SnapshotEntity> snapshots = new HashSet<>();

    private Boolean closed = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public BidEntity() {
    }

    @PrePersist
    protected void onCreate() {
        setCreatedAt(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public TimeLeft getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(TimeLeft timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public Long getBuyout() {
        return buyout;
    }

    public void setBuyout(Long buyout) {
        this.buyout = buyout;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Set<SnapshotEntity> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<SnapshotEntity> snapshots) {
        this.snapshots = snapshots;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BidEntity bid = (BidEntity) o;

        return identifier.equals(bid.identifier);

    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }

    public enum TimeLeft {
        SHORT,
        MEDIUM,
        LONG,
        VERY_LONG
    }
}
