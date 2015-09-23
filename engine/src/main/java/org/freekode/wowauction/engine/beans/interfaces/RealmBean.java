package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.Realm;

import java.util.List;

public interface RealmBean {
    Realm save(Realm realm);

    Realm getById(int id);

    List<Realm> findForUpdate();

    List<Realm> findAll();
}
