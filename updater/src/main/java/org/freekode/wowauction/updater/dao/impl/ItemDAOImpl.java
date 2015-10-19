package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.ItemDAO;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@Transactional
public class ItemDAOImpl implements ItemDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void createAll(Set<ItemEntity> entitySet) {
        for (ItemEntity entity : entitySet) {
            entityManager.persist(entity);
        }
    }

    @Override
    public ItemEntity save(ItemEntity item) {
        return entityManager.merge(item);
    }

    @Override
    public Set<ItemEntity> updateAll(Set<ItemEntity> entitySet) {
        Set<ItemEntity> updated = new HashSet<>();
        for (ItemEntity entity : entitySet) {
            updated.add(entityManager.merge(entity));
        }

        return updated;
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
