package org.freekode.wowauction.persistence.models;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    private BigInteger rate;

    private BigInteger buyout;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TimeLeft timeLeft;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @ManyToMany
    @JoinTable(name = "snapshot_bid",
            joinColumns = {@JoinColumn(name = "bidId")},
            inverseJoinColumns = {@JoinColumn(name = "snapshotId")})
    private Set<Snapshot> snapshots = new HashSet<>();

    private Boolean closed = false;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Bid() {
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
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

    public BigInteger getRate() {
        return rate;
    }

    public void setRate(BigInteger rate) {
        this.rate = rate;
    }

    public BigInteger getBuyout() {
        return buyout;
    }

    public void setBuyout(BigInteger buyout) {
        this.buyout = buyout;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Set<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<Snapshot> snapshots) {
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

        Bid bid = (Bid) o;

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
