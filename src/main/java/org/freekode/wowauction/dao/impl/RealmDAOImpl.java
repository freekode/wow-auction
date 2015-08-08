package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Realm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RealmDAOImpl implements RealmDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    @Override
    public void create(Realm realm) {
        entityManager.persist(realm);
    }

    @Override
    public Realm getById(Integer id) {
        return entityManager.find(Realm.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Realm> findAll() {
        return entityManager.createQuery("select realm from Realm realm").getResultList();
    }
}
