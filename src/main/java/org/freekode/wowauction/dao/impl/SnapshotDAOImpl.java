package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findByToday() {
	    Date todayStart, todayEnd;
	    Calendar today = Calendar.getInstance();

	    today.set(Calendar.HOUR_OF_DAY, 0);
	    today.set(Calendar.MINUTE, 0);
	    today.set(Calendar.SECOND, 0);
	    today.set(Calendar.MILLISECOND, 0);

	    todayStart = today.getTime();

	    today.set(Calendar.HOUR_OF_DAY, 23);
	    today.set(Calendar.MINUTE, 59);
	    today.set(Calendar.SECOND, 59);
	    today.set(Calendar.MILLISECOND, 1000);

	    todayEnd = today.getTime();


        Query query = entityManager.createQuery(
			"select snapshot from Snapshot snapshot " +
					"where snapshot.lastModified > :starttime " +
					"and snapshot.lastModified < :endtime"
        );

	    query.setParameter("starttime", todayStart);
	    query.setParameter("endtime", todayEnd);

	    return query.getResultList();
    }
}
