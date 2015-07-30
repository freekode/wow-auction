package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
import org.freekode.wowauction.models.Realm;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.freekode.wowauction.models.Snapshot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Component
public class SnapshotDAOImpl implements SnapshotDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(Snapshot snapshot) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(snapshot);
        tx.commit();
        session.close();
    }

    @Override
    public Snapshot getById(Integer id) {
        Session session = sessionFactory.openSession();
        Snapshot snapshot = (Snapshot) session.get(Snapshot.class, id);
        session.close();
        return snapshot;
    }

    @Override
    public Snapshot getLast(Realm realm) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Snapshot where realm = :realm order by lastModified desc");
        query.setParameter("realm", realm);
        Snapshot snapshot = (Snapshot) query.setMaxResults(1).uniqueResult();
        session.close();
        return snapshot;

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Snapshot> getAll() {
        Session session = sessionFactory.openSession();
        List<Snapshot> snapshotList = session.createQuery("from Snapshot").list();
        session.close();
        return snapshotList;
    }
}
