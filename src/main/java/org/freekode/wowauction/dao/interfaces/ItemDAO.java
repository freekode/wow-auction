package org.freekode.wowauction.dao.interfaces;


import org.freekode.wowauction.models.CatalogEntryEntity;
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

    List<Item> find(String identifier, String suffixId, String uniqueId, String context,
                    String name, Integer minLevel, Integer maxLevel, CatalogEntryEntity quality,
                    CatalogEntryEntity itemClass, CatalogEntryEntity itemSubclass, CatalogEntryEntity inventorySlot,
                    Set options);

    Item getItem(int id, Set options);

    List<Item> getList(Integer page, Integer amount, Set options);
}
