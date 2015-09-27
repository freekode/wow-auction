package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.persistence.models.BidEntity;

import java.util.Set;

public class Bid extends BaseTransfer<BidEntity> {
    public Bid() {
        super();
        entity = new BidEntity();
    }

    public Bid(BidEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {

    }

    public enum Options {
        INIT_SNAPSHOTS
    }
}
