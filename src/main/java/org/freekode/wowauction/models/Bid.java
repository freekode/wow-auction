package org.freekode.wowauction.models;


import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    private Long id;

    private Integer bid;

    private Integer buyout;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "playerId")
    private Player player;

    @Enumerated(EnumType.STRING)
    private TimeLeft timeLeft;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Bid() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getBuyout() {
        return buyout;
    }

    public void setBuyout(Integer buyout) {
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
