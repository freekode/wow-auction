package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.engine.services.Utils;
import org.freekode.wowauction.engine.transfer.Snapshot;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.RealmEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    public SnapshotEntity getEntity(int id) {
        return entityManager.find(SnapshotEntity.class, id);
    }

    @Override
    public Snapshot getSnapshot(int id, Set options) {
        SnapshotEntity entity = getEntity(id);
        if (entity != null) {
            Snapshot snapshot = new Snapshot(entity);
            snapshot.init(options);
            return snapshot;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Snapshot getLast(RealmEntity realm, Set options) {
        Query query = entityManager.createQuery(
                "select snapshot " +
                        "from SnapshotEntity snapshot " +
                        "where snapshot.realm = :realm " +
                        "order by snapshot.lastModified desc");

        query.setParameter("realm", realm);

        List<SnapshotEntity> snapshots = query.getResultList();
        if (!snapshots.isEmpty()) {
            Snapshot snapshot = new Snapshot(snapshots.get(0));
            snapshot.init(options);
            return snapshot;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findAll(Set options) {
        Query query = entityManager.createQuery("select snapshot from SnapshotEntity snapshot");

        List<SnapshotEntity> entities = query.getResultList();

        List<Snapshot> list = new ArrayList<>();
        for (SnapshotEntity entity : entities) {
            list.add(new Snapshot(entity));
        }
        Utils.initCollection(list, options);

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findByBid(BidEntity bid, Set options) {
        Query query = entityManager.createQuery("select bid.snapshots from BidEntity bid where bid = :bid");
        query.setParameter("bid", bid);

        List<SnapshotEntity> entities = query.getResultList();

        List<Snapshot> list = new ArrayList<>();
        for (SnapshotEntity entity : entities) {
            list.add(new Snapshot(entity));
        }
        Utils.initCollection(list, options);

        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> findBetweenDates(int realmId, Date startTime, Date endTime, Set options) {
        Query query = entityManager.createQuery(
                "select snapshot from SnapshotEntity snapshot " +
                        "where snapshot.lastModified > :starttime " +
                        "and snapshot.lastModified < :endtime " +
                        "and snapshot.realm.id = :realmId"
        );

        query.setParameter("starttime", startTime);
        query.setParameter("endtime", endTime);
        query.setParameter("realmId", realmId);


        List<SnapshotEntity> entities = query.getResultList();

        List<Snapshot> list = new ArrayList<>();
        for (SnapshotEntity entity : entities) {
            list.add(new Snapshot(entity));
        }
        Utils.initCollection(list, options);

        return list;
    }
}
