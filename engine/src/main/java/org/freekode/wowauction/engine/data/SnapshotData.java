package org.freekode.wowauction.engine.data;

import org.freekode.wowauction.engine.transfer.Snapshot;

import java.util.Date;

public class SnapshotData {
    private String file;

    private Date lastModified;

    private Integer closed;

    private Integer existing;

    private Integer newAmount;

    private Date createdAt;

    private RealmData realm;


    public SnapshotData() {
    }

    public SnapshotData(Snapshot snapshot) {
        this.file = snapshot.getFile();
        this.lastModified = snapshot.getLastModified();
        this.closed = snapshot.getClosed();
        this.existing = snapshot.getExisting();
        this.newAmount = snapshot.getNewAmount();
        this.createdAt = snapshot.getCreatedAt();
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

    public Integer getClosed() {
        return closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    public Integer getExisting() {
        return existing;
    }

    public void setExisting(Integer existing) {
        this.existing = existing;
    }

    public Integer getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(Integer newAmount) {
        this.newAmount = newAmount;
    }

    public RealmData getRealm() {
        return realm;
    }

    public void setRealm(RealmData realm) {
        this.realm = realm;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
