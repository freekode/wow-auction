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
        for (ItemEntity item : items) {
            ItemEntity addedItem;

            ItemEntity existingItem = itemDAO.isExistsByConstraint(item);
            if (existingItem == null) {
                addedItem = itemDAO.save(item);
            } else {
                existingItem.setBids(item.getBids());
                addedItem = itemDAO.save(existingItem);
            }

            addedItems.add(addedItem);
        }

        return addedItems;
    }
}
