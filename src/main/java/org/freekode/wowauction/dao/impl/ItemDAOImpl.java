package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.tools.Utils;
import org.freekode.wowauction.transfer.Item;
import org.freekode.wowauction.models.ItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @Override
    public ItemEntity save(ItemEntity item) {
        return entityManager.merge(item);
    }

    @Override
    public ItemEntity getEntity(int id) {
        ItemEntity item = entityManager.find(ItemEntity.class, id);
        item.getBids().size();
        return item;
    }

    @Override
    public Item getItem(int id, Set options) {
        ItemEntity entity = getEntity(id);
        if (entity != null) {
            Item item = new Item(entity);
            item.init(options);
            return item;
        }

        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Item> getList(Integer page, Integer amount, Set options) {
        if (page > 0) {
            page--;
        }

        Query query = entityManager.createQuery("select item from ItemEntity item order by item.id");

        if (page != null && amount != null) {
            query.setFirstResult(page * amount).setMaxResults(amount);
        }

        List<ItemEntity> entities = query.getResultList();
        List<Item> list = new ArrayList<>();
        for (ItemEntity entity : entities) {
            list.add(new Item(entity));
        }
        Utils.initCollection(list, options);

        return list;
    }
}
