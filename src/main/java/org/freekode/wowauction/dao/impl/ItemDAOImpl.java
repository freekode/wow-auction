package org.freekode.wowauction.dao.impl;

import com.sun.istack.internal.Nullable;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.CatalogEntryEntity;
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
    public List<Item> find(@Nullable String identifier, @Nullable String suffixId, @Nullable String uniqueId, @Nullable String context,
                           @Nullable String name, @Nullable Integer minLevel, @Nullable Integer maxLevel, @Nullable CatalogEntryEntity quality,
                           CatalogEntryEntity itemClass, CatalogEntryEntity itemSubclass, CatalogEntryEntity inventorySlot,
                           Set options) {

        StringBuilder hql = new StringBuilder("select i from ItemEntity i where (1=1) ");

        if (identifier != null) {
            hql.append("and (i.identifier = :identifier) ");
        }
        if (suffixId != null) {
            hql.append("and (i.suffixId = :suffixId) ");
        }
        if (uniqueId != null) {
            hql.append("and (i.uniqueId = :uniqueId) ");
        }
        if (context != null) {
            hql.append("and (i.context = :context) ");
        }
        if (name != null) {
            hql.append("and (i.itemInfo.name = :name) ");
        }
        if (minLevel != null) {
            hql.append("and (i.itemInfo.level >= :minLevel) ");
        }
        if (maxLevel != null) {
            hql.append("and (i.itemInfo.level <= :maxLevel) ");
        }
        if (quality != null) {
            hql.append("and (i.itemInfo.quality = :quality) ");
        }
        if (itemClass != null) {
            hql.append("and (i.itemInfo.itemClass <= :itemClass) ");
        }
        if (itemSubclass != null) {
            hql.append("and (i.itemInfo.itemSubclass <= :itemSubclass) ");
        }
        if (inventorySlot != null) {
            hql.append("and (i.itemInfo.inventorySlot <= :inventorySlot) ");
        }

        Query query = entityManager.createQuery(hql.toString());

        if (identifier != null) {
            query.setParameter("identifier", identifier);
        }
        if (suffixId != null) {
            query.setParameter("suffixId", suffixId);
        }
        if (uniqueId != null) {
            query.setParameter("uniqueId", uniqueId);
        }
        if (context != null) {
            query.setParameter("context", context);
        }
        if (name != null) {
            query.setParameter("name", name);
        }
        if (minLevel != null) {
            query.setParameter("minLevel", minLevel);
        }
        if (maxLevel != null) {
            query.setParameter("maxLevel", maxLevel);
        }
        if (quality != null) {
            query.setParameter("quality", quality);
        }
        if (itemClass != null) {
            query.setParameter("itemClass", itemClass);
        }
        if (itemSubclass != null) {
            query.setParameter("itemSubclass", itemSubclass);
        }
        if (inventorySlot != null) {
            query.setParameter("inventorySlot", inventorySlot);
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
