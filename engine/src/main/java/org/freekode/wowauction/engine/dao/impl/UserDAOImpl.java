package org.freekode.wowauction.engine.dao.impl;

import org.freekode.wowauction.engine.dao.interfaces.UserDAO;
import org.freekode.wowauction.persistence.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User authenticate(User user) {
        Query query = entityManager.createQuery("select user from User user where user.login = :login and user.password = :password");
        query.setParameter("login", user);
        query.setParameter("password", user);

        return (User) query.getSingleResult();
    }

    @Override
    public User getById(Integer id) {
        return entityManager.find(User.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select user from User user").getResultList();
    }
}
