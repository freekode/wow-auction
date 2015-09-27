package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.RealmEntity;

import java.util.List;

public interface RealmBean {
    RealmEntity save(RealmEntity realm);

    RealmEntity getById(int id);

    List<RealmEntity> findForUpdate();

    List<RealmEntity> findAll();
}
