package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

public interface ItemDAO {
    ItemEntity save(ItemEntity item);

    ItemEntity isExistsByConstraint(ItemEntity item);
}
