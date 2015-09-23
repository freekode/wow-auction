package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Item save(Item item) {
        return entityManager.merge(item);
    }

    @Override
    public Item getById(Integer id) {
        Item item = entityManager.find(Item.class, id);
        item.getBids().size();
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Item> findAll() {
        return entityManager.createQuery("select item from Item item").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Item isExistsByConstraint(Item item) {
        Query query = entityManager.createQuery("select item from Item item where item.identifier = :identifier and item.uniqueId = :uniqueId");
        query.setParameter("identifier", item.getIdentifier());
        query.setParameter("uniqueId", item.getUniqueId());

        List<Item> items = query.getResultList();
        if (!items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }
}
