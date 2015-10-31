package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.RealmBean;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.transfer.Realm;
import org.freekode.wowauction.models.RealmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class RealmBeanImpl implements RealmBean {
    @Autowired
    private RealmDAO realmDAO;


    @Override
    public RealmEntity save(RealmEntity realm) {
        return realmDAO.save(realm);
    }

    @Override
    public RealmEntity getEntity(int id) {
        return realmDAO.getEntity(id);
    }

    @Override
    public Realm getRealm(int id, Set options) {
        return realmDAO.getRealm(id, options);
    }

    @Override
    public List<Realm> findAll(Set options) {
        return realmDAO.findAll(options);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RealmEntity> findForUpdate() {
        List<RealmEntity> realms = new ArrayList<>();
        for (Realm realm : findAll(null)) {
            if (realm.getUpdating()) {
                realms.add(realm.getEntity());
            }
        }

        return realms;
    }
}
