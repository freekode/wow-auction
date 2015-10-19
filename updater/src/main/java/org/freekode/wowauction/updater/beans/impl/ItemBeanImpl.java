package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.ItemBean;
import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;


    @Override
    public Set<ItemEntity> updateOrCreateAll(Set<ItemEntity> items) {
        Set<ItemEntity> addedItems = new HashSet<>();

        Set<ItemEntity> createItems = new HashSet<>();
        Set<ItemEntity> updateItems = new HashSet<>();
        for (ItemEntity item : items) {
            ItemEntity existingItem = itemDAO.isExistsByConstraint(item);
            if (existingItem == null) {
                createItems.add(item);
            } else {
                existingItem.setBids(item.getBids());
                updateItems.add(existingItem);
            }
        }

        itemDAO.createAll(createItems);
        itemDAO.updateAll(updateItems);

        return addedItems;
    }
}
