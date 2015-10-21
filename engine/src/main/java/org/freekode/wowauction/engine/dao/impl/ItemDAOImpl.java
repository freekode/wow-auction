package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.ItemDAO;
import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.engine.transfer.Item;
import org.freekode.wowauction.persistence.models.ItemEntity;
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
