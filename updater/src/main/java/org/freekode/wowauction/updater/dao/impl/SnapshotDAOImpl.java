package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Realm;
import org.freekode.wowauction.persistence.models.Snapshot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

@Repository
@Transactional
public class SnapshotDAOImpl implements SnapshotDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Snapshot save(Snapshot snapshot) {
        return entityManager.merge(snapshot);
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
    public List<Snapshot> findByBid(Bid bid) {
        Query query = entityManager.createQuery("select bid.snapshots from Bid bid where bid = :bid");
        query.setParameter("bid", bid);

        return query.getResultList();
    }
}
