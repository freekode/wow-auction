package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.updater.dao.interfaces.RealmDAO;
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


    @SuppressWarnings("unchecked")
    @Override
    public List<RealmEntity> findAll() {
        return entityManager.createQuery("select realm from RealmEntity realm").getResultList();
    }
}
