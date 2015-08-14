package org.freekode.wowauction.beans.interfaces;

import org.freekode.wowauction.models.Realm;

import java.util.List;

public interface RealmBean {
    Realm save(Realm realm);

    Realm getById(int id);

    List<Realm> findForUpdate();

    List<Realm> findAll();
}
