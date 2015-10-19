package org.freekode.wowauction.updater.beans.impl;

import org.freekode.wowauction.updater.beans.interfaces.ItemBean;
import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;



    @Override
    public List<ItemEntity> saveAll(List<ItemEntity> items) {
        List<ItemEntity> updateItems = new ArrayList<>();
        for (ItemEntity item : items) {
            ItemEntity existingItem = itemDAO.isExistsByConstraint(item);
            if (existingItem != null) {
                existingItem.setBids(item.getBids());
                updateItems.add(existingItem);
                continue;
            }

            updateItems.add(item);
        }


        return itemDAO.updateAll(updateItems);
    }
}
