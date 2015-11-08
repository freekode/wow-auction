package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.tools.Utils;
import org.freekode.wowauction.transfer.Item;
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
                existingItem.setRand(item.getRand());
                existingItem.setSeed(item.getSeed());
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
        Query query = entityManager.createQuery("select item from ItemEntity item where item.identifier = :identifier and item.seed = :seed");
        query.setParameter("identifier", item.getIdentifier());
        query.setParameter("seed", item.getSeed());

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
    public List<Item> findItem(String identifier, String rand, String seed, String context, Set options) {

        StringBuilder hql = new StringBuilder("select i from ItemEntity i where (1=1) ");

        if (identifier != null) {
            hql.append("and (i.identifier = :identifier) ");
        }
        if (rand != null) {
            hql.append("and (i.rand = :rand) ");
        }
        if (seed != null) {
            hql.append("and (i.seed = :seed) ");
        }
        if (context != null) {
            hql.append("and (i.context = :context) ");
        }

        Query query = entityManager.createQuery(hql.toString());

        if (identifier != null) {
            query.setParameter("identifier", identifier);
        }
        if (rand != null) {
            query.setParameter("rand", rand);
        }
        if (seed != null) {
            query.setParameter("seed", seed);
        }
        if (context != null) {
            query.setParameter("context", context);
        }


        List<ItemEntity> entities = query.getResultList();
        List<Item> list = new ArrayList<>();
        for (ItemEntity entity : entities) {
            list.add(new Item(entity));
        }
        Utils.initCollection(list, options);

        return list;
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
