package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.Set;

public interface ItemDAO {
    void createAll(Set<ItemEntity> entitySet);

    ItemEntity save(ItemEntity item);

    Set<ItemEntity> updateAll(Set<ItemEntity> entitySet);

    ItemEntity isExistsByConstraint(ItemEntity item);
}
