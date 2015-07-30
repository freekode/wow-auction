package org.freekode.wowauction.models;

import org.json.simple.JSONObject;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "realms")
public class Realm implements SerializableToJson {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private Region region;

    private String name;

    private String slug;

    @OneToMany(mappedBy = "realm")
    private Set<Snapshot> snapshots = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Realm() {
    }

    @PrePersist
    protected void onCreate() {
        setCreatedAt(new Date());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public Set<Snapshot> getSnapshots() {
        return snapshots;
    }

    public void setSnapshots(Set<Snapshot> snapshots) {
        this.snapshots = snapshots;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @SuppressWarnings("unchecked")
    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", getId());
        jsonObject.put("region", getRegion().toString());
        jsonObject.put("name", getName());
        jsonObject.put("slug", getSlug());

        if (getCreatedAt() != null) jsonObject.put("createdAt", getCreatedAt());

        return jsonObject;
    }

    public enum Region {
        EU, US
    }
}
