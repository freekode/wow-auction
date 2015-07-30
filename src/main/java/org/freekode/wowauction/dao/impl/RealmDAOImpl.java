package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Realm;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RealmDAOImpl implements RealmDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void create(Realm realm) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(realm);
        tx.commit();
        session.close();
    }

    @Override
    public Realm getById(Integer id) {
        Session session = sessionFactory.openSession();
        Realm realm = (Realm) session.get(Realm.class, id);
        session.close();
        return realm;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Realm> getAll() {
//        Session session = sessionFactory.openSession();
//        List<Realm> realms = session.createQuery("from Realm").list();
//        session.close();
//        return realms;

        System.out.println(entityManager);

        return new ArrayList<>();
    }
}
