package org.freekode.wowauction.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "players")
public class PlayerEntity {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "player")
    private Set<BidEntity> bids = new HashSet<>();


    public PlayerEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<BidEntity> getBids() {
        return bids;
    }

    public void setBids(Set<BidEntity> bids) {
        this.bids = bids;
    }
}
