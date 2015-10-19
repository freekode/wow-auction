package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemDAO {
    List<ItemEntity> updateAll(List<ItemEntity> entitySet);

    ItemEntity isExistsByConstraint(ItemEntity item);
}
