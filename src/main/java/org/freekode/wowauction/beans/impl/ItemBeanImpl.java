package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;


    @Transactional
    @Override
    public Set<Item> updateOrCreateAll(Set<Item> items) {
        Set<Item> addedItems = new HashSet<>();
        for (Item item : items) {
            Item addedItem;

            Item existingItem = itemDAO.isExistsByConstraint(item);
            if (existingItem == null) {
                addedItem = itemDAO.create(item);
            } else {
                existingItem.setBids(item.getBids());
                addedItem = itemDAO.create(existingItem);
            }

            addedItems.add(addedItem);
        }

        return addedItems;
    }
}
