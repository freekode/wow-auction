package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemDAO {
    List<ItemEntity> createAll(List<ItemEntity> items);

    List<ItemEntity> updateAll(List<ItemEntity> items);

    ItemEntity isExistsByConstraint(ItemEntity item);
}
