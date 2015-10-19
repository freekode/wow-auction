package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.persistence.models.ItemEntity;
import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<ItemEntity> saveAll(List<ItemEntity> items) {
        List<ItemEntity> updateItems = new ArrayList<>();
        for (ItemEntity item : items) {
            ItemEntity updateItem;

            ItemEntity existingItem = isExistsByConstraint(item);
            if (existingItem == null) {
                updateItem = item;
            } else {
                existingItem.setBids(item.getBids());
                updateItem = existingItem;
            }

            updateItems.add(entityManager.merge(updateItem));
        }


        return updateItems;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ItemEntity isExistsByConstraint(ItemEntity item) {
        Query query = entityManager.createQuery("select item from ItemEntity item where item.identifier = :identifier and item.uniqueId = :uniqueId");
        query.setParameter("identifier", item.getIdentifier());
        query.setParameter("uniqueId", item.getUniqueId());

        List<ItemEntity> items = query.getResultList();
        if (!items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }
}
