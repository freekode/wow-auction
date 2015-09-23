package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.RealmDAO;
import org.freekode.wowauction.persistence.models.Realm;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class RealmDAOImpl implements RealmDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Realm save(Realm realm) {
        return entityManager.merge(realm);
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
