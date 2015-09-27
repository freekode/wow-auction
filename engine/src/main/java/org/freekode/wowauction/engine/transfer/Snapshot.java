package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Snapshot extends BaseTransfer<SnapshotEntity> implements Initializable {
    private List<Bid> bids = new ArrayList<>();


    public Snapshot() {
        super();
        entity = new SnapshotEntity();
    }

    public Snapshot(SnapshotEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
        if (options != null && options.contains(Options.INIT_BIDS)) {
            Utils.initCollection(entity.getBids(), options);
            for (BidEntity bidEntity : entity.getBids()) {
                Bid bid = new Bid(bidEntity);
                bid.init(options);
                bids.add(bid);
            }
        }
    }

    public List<Bid> getBids() {
        return bids;
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getFile() {
        return entity.getFile();
    }

    public Date getLastModified() {
        return entity.getLastModified();
    }

    public Integer getClosed() {
        return entity.getClosed();
    }

    public Integer getExisting() {
        return entity.getExisting();
    }

    public Integer getNewAmount() {
        return entity.getNewAmount();
    }

    public Date getCreatedAt() {
        return entity.getCreatedAt();
    }

    public enum Options {
        INIT_BIDS
    }
}
