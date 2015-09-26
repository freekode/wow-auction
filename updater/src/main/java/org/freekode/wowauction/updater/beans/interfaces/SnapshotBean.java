package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Realm;
import org.freekode.wowauction.persistence.models.Snapshot;

import java.util.Date;
import java.util.List;

public interface SnapshotBean {
    Snapshot save(Snapshot snapshot);

    Snapshot getLastByRealm(Realm realm);

    List<Snapshot> getByBid(Bid bid);
}
