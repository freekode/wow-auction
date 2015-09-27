package org.freekode.wowauction.engine.transfer;

import org.freekode.wowauction.persistence.models.SnapshotEntity;

import java.util.Set;

public class Snapshot extends BaseTransfer<SnapshotEntity> {
    public Snapshot() {
        super();
        entity = new SnapshotEntity();
    }

    public Snapshot(SnapshotEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {

    }

    public enum Options {
        INIT_BIDS
    }
}
