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
    public List<ItemEntity> createAll(List<ItemEntity> items) {
        List<ItemEntity> createItems = new ArrayList<>();
        for (ItemEntity item : items) {
            if (isExistsByConstraint(item) == null) {
                createItems.add(entityManager.merge(item));
            }
        }

        return createItems;
    }

    @Override
    public List<ItemEntity> updateAll(List<ItemEntity> items) {
        List<ItemEntity> updateItems = new ArrayList<>();
        for (ItemEntity item : items) {
            ItemEntity existingItem = isExistsByConstraint(item);
            if (existingItem != null) {
                existingItem.setIdentifier(item.getIdentifier());
                existingItem.setSuffixId(item.getSuffixId());
                existingItem.setUniqueId(item.getUniqueId());
                existingItem.setContext(item.getContext());
                existingItem.setCreatedAt(item.getCreatedAt());
                existingItem.setItemInfo(item.getItemInfo());
                existingItem.setBids(item.getBids());

                updateItems.add(entityManager.merge(existingItem));
            }
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
