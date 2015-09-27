package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.RealmBean;
import org.freekode.wowauction.engine.dao.interfaces.RealmDAO;
import org.freekode.wowauction.engine.transfer.Realm;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
}
