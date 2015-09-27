package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Bid extends BaseTransfer<BidEntity> implements Initializable {
    private List<Snapshot> snapshots = new ArrayList<>();


    public Bid() {
        super();
        entity = new BidEntity();
    }

    public Bid(BidEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
        if (options != null && options.contains(Options.INIT_SNAPSHOTS)) {
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



    public enum Options {
        INIT_SNAPSHOTS
    }
}
