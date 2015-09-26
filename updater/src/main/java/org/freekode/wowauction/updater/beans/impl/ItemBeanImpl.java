package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.ItemBean;
import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;


    @Override
    public Set<Item> updateOrCreateAll(Set<Item> items) {
        Set<Item> addedItems = new HashSet<>();
        for (Item item : items) {
            Item addedItem;

            Item existingItem = itemDAO.isExistsByConstraint(item);
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
