package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.RealmEntity;

import java.util.List;

public interface RealmDAO {
    List<RealmEntity> findAll();
}
