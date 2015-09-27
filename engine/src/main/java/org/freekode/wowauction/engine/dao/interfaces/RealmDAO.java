package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.RealmEntity;

import java.util.List;

public interface RealmDAO {
    RealmEntity save(RealmEntity realm);

    RealmEntity getById(Integer id);

    List<RealmEntity> findAll();
}
