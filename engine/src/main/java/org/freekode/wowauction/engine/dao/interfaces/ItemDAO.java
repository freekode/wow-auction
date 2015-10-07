package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.engine.transfer.Item;
import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemDAO {
    ItemEntity save(ItemEntity item);

    ItemEntity getEntity(int id);

    Item getItem(int id, Set options);

    List<Item> getList(Integer page, Integer amount, Set options);
}
