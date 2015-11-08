package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.models.ItemInfoEntity;
import org.freekode.wowauction.transfer.Item;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    ItemEntity save(ItemEntity item);

    ItemEntity getEntity(Integer id);

    List<Item> getList(Integer page, Integer amount, Set options);

    List<ItemEntity> createAll(List<ItemEntity> items);

    List<ItemEntity> updateAll(List<ItemEntity> items);

    Item getByIdentifier(String identifier, Set options);

    List<Item> findAll(Set options);

    List<Item> findItem(String identifier, String rand, String seed, String context, Set options);

    ItemInfoEntity buildItemInfo(String name, Integer level, String icon, Integer quality, Integer itemClass,
                                 Integer itemSubclass, Integer inventorySlot, Long sellPrice,
                                 ItemEntity item);
}
