package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Realm;

import java.util.List;

public interface RealmDAO {
    void create(Realm realm);

    Realm getById(Integer id);

    List<Realm> findAll();
}
