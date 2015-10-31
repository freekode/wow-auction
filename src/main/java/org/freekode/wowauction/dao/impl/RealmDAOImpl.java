package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.tools.Utils;
import org.freekode.wowauction.transfer.Realm;
import org.freekode.wowauction.models.RealmEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public RealmEntity getEntity(int id) {
        return entityManager.find(RealmEntity.class, id);
    }

    @Override
    public Realm getRealm(int id, Set options) {
        RealmEntity entity = getEntity(id);
        if (entity != null) {
            Realm realm = new Realm(entity);
            realm.init(options);
            return realm;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Realm> findAll(Set options) {
        Query query = entityManager.createQuery("select realm from RealmEntity realm");

        List<RealmEntity> entities = query.getResultList();

        List<Realm> list = new ArrayList<>();
        for (RealmEntity entity : entities) {
            list.add(new Realm(entity));
        }
        Utils.initCollection(list, options);

        return list;
    }
}
