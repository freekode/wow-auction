package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.engine.transfer.Realm;
import org.freekode.wowauction.persistence.models.RealmEntity;

import java.util.List;
import java.util.Set;

public interface RealmBean {
    RealmEntity save(RealmEntity realm);

    RealmEntity getEntity(int id);

    Realm getRealm(int id, Set options);

    List<Realm> findAll(Set options);
}
