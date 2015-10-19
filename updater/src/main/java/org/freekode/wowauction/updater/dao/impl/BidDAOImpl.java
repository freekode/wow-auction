package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.BidDAO;
import org.freekode.wowauction.persistence.models.BidEntity;
import org.freekode.wowauction.persistence.models.SnapshotEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class BidDAOImpl implements BidDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BidEntity> saveAll(List<BidEntity> bids) {
        List<BidEntity> addedBids = new ArrayList<>();
        for (BidEntity bid : bids) {
            BidEntity addedItem = entityManager.merge(bid);
            addedBids.add(addedItem);
        }

        return addedBids;
    }

    @Override
    public List<BidEntity> closeAll(List<BidEntity> bids) {
        for (BidEntity bid : bids) {
            bid.setClosed(true);
        }

        return saveAll(bids);
    }

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

