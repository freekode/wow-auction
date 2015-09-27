package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.ItemEntity;
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
    public ItemEntity save(ItemEntity item) {
        return entityManager.merge(item);
    }

    @Override
    public ItemEntity getById(Integer id) {
        ItemEntity item = entityManager.find(ItemEntity.class, id);
        item.getBids().size();
        return item;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ItemEntity> findAll() {
        return entityManager.createQuery("select item from ItemEntity item").getResultList();
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
