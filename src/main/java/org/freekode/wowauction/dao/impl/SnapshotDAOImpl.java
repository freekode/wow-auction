package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SnapshotDAOImpl implements SnapshotDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void create(Snapshot snapshot) {
        entityManager.persist(snapshot);
    }

    @Override
    public Snapshot getById(Integer id) {
        return entityManager.find(Snapshot.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Snapshot getLast(Realm realm) {
        Query query = entityManager.createQuery("from Snapshot where realm = :realm order by lastModified desc");
        query.setParameter("realm", realm);

        List<Snapshot> snapshots = query.getResultList();
        if (!snapshots.isEmpty()) {
            return snapshots.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> getAll() {
        return entityManager.createQuery("from Snapshot").getResultList();
    }
}
