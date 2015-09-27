package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;

public interface ItemDAO {
    ItemEntity save(ItemEntity item);

    ItemEntity getById(Integer id);

    List<ItemEntity> findAll();

    ItemEntity isExistsByConstraint(ItemEntity item);
}
