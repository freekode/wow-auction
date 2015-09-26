package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.Realm;

import java.util.List;

public interface RealmDAO {
    List<Realm> findAll();
}
