package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.BidDAO;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.Bid;
import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    @Override
    public Bid getById(Integer id) {
        return entityManager.find(Bid.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bid> getAll() {
        return entityManager.createQuery("from Bid").getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Bid> getAllByRealm(Realm realm) {
        Query query = entityManager.createQuery("from Bid where snapshot.realm = :realm");
        query.setParameter("realm", realm);

        return query.getResultList();
    }
}

