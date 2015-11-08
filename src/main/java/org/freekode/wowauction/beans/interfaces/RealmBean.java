package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.models.RealmEntity;
import org.freekode.wowauction.transfer.Realm;

import java.util.List;
import java.util.Set;

public interface RealmBean {
    RealmEntity save(RealmEntity realm);

    RealmEntity getEntity(int id);

    Realm getRealm(int id, Set options);

    List<Realm> findAll(Set options);

    List<RealmEntity> findForUpdate();
}
