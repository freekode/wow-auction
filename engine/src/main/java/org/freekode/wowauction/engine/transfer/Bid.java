package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Bid extends BaseTransfer<BidEntity> implements Initializable {
    private List<Snapshot> snapshots;


    public Bid() {
        super();
        entity = new BidEntity();
    }

    public Bid(BidEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
        if (options != null && options.contains(Options.INIT_BID_SNAPSHOTS)) {
            snapshots = new ArrayList<>();

            Utils.initCollection(entity.getSnapshots(), options);
            for (SnapshotEntity snapshotEntity : entity.getSnapshots()) {
                Snapshot snapshot = new Snapshot(snapshotEntity);
                snapshot.init(options);
                snapshots.add(snapshot);
            }
        }
    }

    public List<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(List<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public Integer getId() {
        return entity.getId();
    }

    public String getIdentifier() {
        return entity.getIdentifier();
    }

    public Long getRate() {
        return entity.getRate();
    }

    public Long getBuyout() {
        return entity.getBuyout();
    }

    public Integer getQuantity() {
        return entity.getQuantity();
    }

    public BidEntity.TimeLeft getTimeLeft() {
        return entity.getTimeLeft();
    }

    public Boolean getClosed() {
        return entity.getClosed();
    }

    public Date getCreatedAt() {
        return entity.getCreatedAt();
    }

    public enum Options {
        INIT_BID_SNAPSHOTS
    }
}
