package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Realm;
import org.freekode.wowauction.persistence.models.Snapshot;

import java.util.Date;
import java.util.List;

public interface SnapshotBean {
    Snapshot save(Snapshot snapshot);

    Snapshot getById(int id);

    Snapshot getLastByRealm(Realm realm);

    List<Snapshot> findAll();

    List<Snapshot> findBetweenDates(Date startTime, Date endTime);

    List<Snapshot> findByToday();

    List<Snapshot> getByBid(Bid bid);
}
