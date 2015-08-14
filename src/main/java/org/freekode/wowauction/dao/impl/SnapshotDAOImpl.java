package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Bid;
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


    @Override
    public Snapshot save(Snapshot snapshot) {
        return entityManager.merge(snapshot);
    }

    @Override
    public Snapshot getById(int id) {
        return entityManager.find(Snapshot.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Snapshot getLast(Realm realm) {
        Query query = entityManager.createQuery(
                "select snapshot " +
                        "from Snapshot snapshot " +
                        "where snapshot.realm = :realm " +
                        "order by snapshot.lastModified desc");

        query.setParameter("realm", realm);

        List<Snapshot> snapshots = query.getResultList();
        if (!snapshots.isEmpty()) {
            return snapshots.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findAll() {
        return entityManager.createQuery("select snapshot from Snapshot snapshot").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findByBid(Bid bid) {
        Query query = entityManager.createQuery("select bid.snapshots from Bid bid where bid = :bid");
        query.setParameter("bid", bid);

        return query.getResultList();
    }
}
