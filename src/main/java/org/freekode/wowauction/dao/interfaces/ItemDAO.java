package org.freekode.wowauction.dao.interfaces;


import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.transfer.Item;

import java.util.List;
import java.util.Set;

public interface ItemDAO {
    List<ItemEntity> createAll(List<ItemEntity> items);

    List<ItemEntity> updateAll(List<ItemEntity> items);

    ItemEntity isExistsByConstraint(ItemEntity item);

    ItemEntity save(ItemEntity item);

    ItemEntity getEntity(int id);

    Item getItem(int id, Set options);

    List<Item> getList(Integer page, Integer amount, Set options);
}
