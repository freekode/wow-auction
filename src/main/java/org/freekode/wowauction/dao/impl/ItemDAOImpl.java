package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class ItemDAOImpl implements ItemDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void create(Item item) {
        entityManager.persist(item);
    }

    @Transactional
    @Override
    public void createAll(Set<Item> items) {
        for (Item item : items) {
            entityManager.persist(item);
        }
    }

    @Transactional
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
}
