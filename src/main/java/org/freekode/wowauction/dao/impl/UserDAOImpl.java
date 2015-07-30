package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.UserDAO;
import org.freekode.wowauction.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void create(User user) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Override
    public User authenticate(User user) {
        Session session = sessionFactory.openSession();
        User authUser = (User) session.createQuery("from User where login = :login and password = :password")
                .setProperties(user)
                .uniqueResult();
        session.close();

        return authUser;
    }

    @Override
    public User getById(Integer id) {
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> getAll() {
        Session session = sessionFactory.openSession();
        List<User> users = session.createQuery("from User").list();
        session.close();
        return users;
    }
}
