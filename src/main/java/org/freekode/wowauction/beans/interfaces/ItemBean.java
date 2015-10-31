package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.models.ItemInfoEntity;
import org.freekode.wowauction.transfer.Item;
import org.freekode.wowauction.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    ItemEntity save(ItemEntity item);

    ItemEntity getEntity(Integer id);

    List<Item> getList(Integer page, Integer amount, Set options);

    List<ItemEntity> createAll(List<ItemEntity> items);

    List<ItemEntity> updateAll(List<ItemEntity> items);

    ItemInfoEntity buildItemInfo(String name, String level, String url, String icon,
                                 Integer quality, Integer itemClass, Integer itemSubclass, Integer inventorySlot,
                                 ItemEntity item);
}
