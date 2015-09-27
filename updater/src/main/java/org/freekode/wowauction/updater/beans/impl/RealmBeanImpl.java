package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.RealmBean;
import org.freekode.wowauction.updater.dao.interfaces.RealmDAO;
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
    public List<RealmEntity> findForUpdate() {
        List<RealmEntity> realms = new ArrayList<>();
        for (RealmEntity realm : realmDAO.findAll()) {
            if (realm.getUpdating()) {
                realms.add(realm);
            }
        }

        return realms;
    }
}
