package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.RealmBean;
import org.freekode.wowauction.engine.dao.interfaces.RealmDAO;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RealmBeanImpl implements RealmBean {
    @Autowired
    private RealmDAO realmDAO;


    @Override
    public RealmEntity save(RealmEntity realm) {
        return realmDAO.save(realm);
    }

    @Override
    public List<RealmEntity> findForUpdate() {
        List<RealmEntity> realms = new ArrayList<>();
        for (RealmEntity realm : realmDAO.findAll()) {
            if (realm.getUpdating()) {
                realms.add(realm);
            }
        }

        return realms;
    }

    @Override
    public RealmEntity getById(int id) {
        return realmDAO.getById(id);
    }

    @Override
    public List<RealmEntity> findAll() {
        return realmDAO.findAll();
    }
}
