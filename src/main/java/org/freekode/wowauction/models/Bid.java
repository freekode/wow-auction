package org.freekode.wowauction.models;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String identifier;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    private BigInteger bid;

    private BigInteger buyout;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private TimeLeft timeLeft;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Bid() {
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

    public BigInteger getBid() {
        return bid;
    }

    public void setBid(BigInteger bid) {
        this.bid = bid;
    }

    public BigInteger getBuyout() {
        return buyout;
    }

    public void setBuyout(BigInteger buyout) {
        this.buyout = buyout;
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

    public enum TimeLeft {
        SHORT,
        MEDIUM,
        LONG,
        VERY_LONG
    }
}
