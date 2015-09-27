package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.RealmEntity;

import java.util.List;

public interface RealmBean {
    List<RealmEntity> findForUpdate();
}
