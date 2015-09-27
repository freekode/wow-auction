package org.freekode.wowauction.engine.beans.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    ItemEntity save(ItemEntity item);

    ItemEntity getById(Integer id);

    List<ItemEntity> findAll();

    Set<ItemEntity> updateOrCreateAll(Set<ItemEntity> items);
}
