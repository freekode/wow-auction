package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.RealmBean;
import org.freekode.wowauction.updater.dao.interfaces.RealmDAO;
import org.freekode.wowauction.persistence.models.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RealmBeanImpl implements RealmBean {
    @Autowired
    private RealmDAO realmDAO;


    @Override
    public Realm save(Realm realm) {
        return realmDAO.save(realm);
    }

    @Override
    public List<Realm> findForUpdate() {
        List<Realm> realms = new ArrayList<>();
        for (Realm realm : realmDAO.findAll()) {
            if (realm.getUpdating()) {
                realms.add(realm);
            }
        }

        return realms;
    }

    @Override
    public Realm getById(int id) {
        return realmDAO.getById(id);
    }

    @Override
    public List<Realm> findAll() {
        return realmDAO.findAll();
    }
}
