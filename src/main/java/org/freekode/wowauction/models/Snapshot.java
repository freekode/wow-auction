package org.freekode.wowauction.models;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "snapshots")
public class Snapshot {
    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String file;

    private Date lastModified;

    @ManyToOne
    @JoinColumn(name = "realmId")
    private Realm realm;

    @OneToMany(mappedBy = "snapshot")
    private Set<Item> items = new HashSet<>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


    public Snapshot() {
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Realm getRealm() {
        return realm;
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}
