package org.freekode.wowauction.engine.data;

import java.util.HashSet;
import java.util.Set;

public class RealmData {
    private Integer id;

    private String region;

    private String name;

    private String slug;

    private Boolean updating = false;

    private Set<SnapshotData> snapshots = new HashSet<>();


    public RealmData() {
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
