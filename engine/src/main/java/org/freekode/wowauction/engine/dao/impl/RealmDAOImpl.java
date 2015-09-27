package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.RealmDAO;
import org.freekode.wowauction.persistence.models.RealmEntity;
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
    public RealmEntity save(RealmEntity realm) {
        return entityManager.merge(realm);
    }

    @Override
    public RealmEntity getById(Integer id) {
        return entityManager.find(RealmEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RealmEntity> findAll() {
        return entityManager.createQuery("select realm from RealmEntity realm").getResultList();
    }
}
