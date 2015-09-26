package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.Realm;

import java.util.List;

public interface RealmBean {
    List<Realm> findForUpdate();
}
