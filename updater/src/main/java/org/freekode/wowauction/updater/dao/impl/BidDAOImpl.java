package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.BidDAO;
import org.freekode.wowauction.persistence.models.Bid;
import org.freekode.wowauction.persistence.models.Snapshot;
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
    public Bid save(Bid bid) {
        return entityManager.merge(bid);
    }

    @Override
    public Bid getById(Integer id) {
        return entityManager.find(Bid.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bid> findAll() {
        return entityManager.createQuery("select bid from Bid bid").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bid> findBySnapshot(Snapshot snapshot) {
        Query query = entityManager.createQuery("select snapshot.bids from Snapshot snapshot where snapshot = :snapshot");
        query.setParameter("snapshot", snapshot);

        return query.getResultList();
    }
}

