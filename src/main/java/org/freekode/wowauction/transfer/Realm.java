package org.freekode.wowauction.transfer;

import org.freekode.wowauction.models.RealmEntity;
import org.freekode.wowauction.models.SnapshotEntity;
import org.freekode.wowauction.tools.Utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Realm extends BaseTransfer<RealmEntity> implements Initializable {
    private List<Snapshot> snapshots;


    public Realm() {
        super();
        entity = new RealmEntity();
    }

    public Realm(RealmEntity entity) {
        super(entity);
    }

    @Override
    public void init(Set options) {
        if (options != null && options.contains(Options.INIT_REALM_SNAPSHOTS)) {
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

    public RealmEntity.Region getRegion() {
        return entity.getRegion();
    }

    public String getName() {
        return entity.getName();
    }

    public String getSlug() {
        return entity.getSlug();
    }

    public Boolean getUpdating() {
        return entity.getUpdating();
    }

    public Date getCreatedAt() {
        return entity.getCreatedAt();
    }

    public enum Options {
        INIT_REALM_SNAPSHOTS
    }
}
