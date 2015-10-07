package org.freekode.wowauction.engine.controllers.data;

import org.freekode.wowauction.engine.transfer.Realm;
import org.freekode.wowauction.engine.transfer.Snapshot;

import java.util.Set;

public class RealmData {
    private Integer id;
    private String region;
    private String name;
    private String slug;
    private Boolean updating;
    private Set<SnapshotData> snapshots;


    public RealmData() {
    }

    public RealmData(Realm realm) {
        this.id = realm.getId();
        this.region = realm.getRegion().toString();
        this.name = realm.getName();
        this.slug = realm.getSlug();
        this.updating = realm.getUpdating();

        for (Snapshot snapshot : realm.getSnapshots()) {
            snapshots.add(new SnapshotData(snapshot));
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Boolean getUpdating() {
        return updating;
    }

    public void setUpdating(Boolean updating) {
        this.updating = updating;
    }

    public Set<SnapshotData> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<SnapshotData> snapshots) {
        this.snapshots = snapshots;
    }
}
