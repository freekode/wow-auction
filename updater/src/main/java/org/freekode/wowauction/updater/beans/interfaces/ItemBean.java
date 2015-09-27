package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.Set;

public interface ItemBean {
    Set<ItemEntity> updateOrCreateAll(Set<ItemEntity> items);
}
