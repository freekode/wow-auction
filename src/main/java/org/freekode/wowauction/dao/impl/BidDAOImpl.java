package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class BidDAOImpl implements BidDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void create(Bid bid) {
        entityManager.persist(bid);
    }

    @Transactional
    @Override
    public void createAll(Set<Bid> bids) {
//        entityManager.getTransaction().begin();
        for (Bid bid : bids) {
            entityManager.persist(bid);
        }
//        entityManager.getTransaction().commit();
    }

    @Transactional
    @Override
    public Bid getById(Long id) {
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

