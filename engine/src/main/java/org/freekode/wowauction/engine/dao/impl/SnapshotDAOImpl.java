package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
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
    public SnapshotEntity save(SnapshotEntity snapshot) {
        return entityManager.merge(snapshot);
    }

    @Override
    public SnapshotEntity getById(int id) {
        return entityManager.find(SnapshotEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public SnapshotEntity getLast(RealmEntity realm) {
        Query query = entityManager.createQuery(
                "select snapshot " +
                        "from SnapshotEntity snapshot " +
                        "where snapshot.realm = :realm " +
                        "order by snapshot.lastModified desc");

        query.setParameter("realm", realm);

        List<SnapshotEntity> snapshots = query.getResultList();
        if (!snapshots.isEmpty()) {
            return snapshots.get(0);
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SnapshotEntity> findAll() {
        return entityManager.createQuery("select snapshot from SnapshotEntity snapshot").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SnapshotEntity> findByBid(BidEntity bid) {
        Query query = entityManager.createQuery("select bid.snapshots from BidEntity bid where bid = :bid");
        query.setParameter("bid", bid);

        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SnapshotEntity> findBetweenDates(Date startTime, Date endTime) {
        Query query = entityManager.createQuery(
			"select snapshot from SnapshotEntity snapshot " +
					"where snapshot.lastModified > :starttime " +
					"and snapshot.lastModified < :endtime"
        );

	    query.setParameter("starttime", startTime);
	    query.setParameter("endtime", endTime);

	    return query.getResultList();
    }
}
