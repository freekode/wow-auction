package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.RealmBean;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Transactional
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
