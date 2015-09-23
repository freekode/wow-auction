package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.Realm;

import java.util.List;

public interface RealmDAO {
    Realm save(Realm realm);

    Realm getById(Integer id);

    List<Realm> findAll();
}
