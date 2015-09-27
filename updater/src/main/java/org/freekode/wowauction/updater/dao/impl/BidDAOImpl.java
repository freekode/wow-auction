package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.BidDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class BidDAOImpl implements BidDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public BidEntity save(BidEntity bid) {
        return entityManager.merge(bid);
    }

    @Override
    public BidEntity getById(Integer id) {
        return entityManager.find(BidEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BidEntity> findBySnapshot(SnapshotEntity snapshot) {
        Query query = entityManager.createQuery("select snapshot.bids from SnapshotEntity snapshot where snapshot = :snapshot");
        query.setParameter("snapshot", snapshot);

        return query.getResultList();
    }
}

