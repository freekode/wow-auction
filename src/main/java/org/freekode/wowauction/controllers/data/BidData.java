package org.freekode.wowauction.controllers.data;

import org.freekode.wowauction.transfer.Bid;

import java.util.Date;

public class BidData {
    private Integer id;
    private String identifier;
    private Long rate;
    private Long buyout;
    private Integer quantity;
    private String timeLeft;
    private Boolean closed;
    private Date createdAt;


    public BidData() {
    }

    public BidData(Bid bid) {
        id = bid.getId();
        identifier = bid.getIdentifier();
        rate = bid.getRate();
        buyout = bid.getBuyout();
        quantity = bid.getQuantity();
        timeLeft = bid.getTimeLeft().name();
        closed = bid.getClosed();
        createdAt = bid.getCreatedAt();
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
        this.timeLeft = timeLeft;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }
}
