package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.UserDAO;
import org.freekode.wowauction.models.UserEntity;
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
    public void save(UserEntity user) {
        entityManager.persist(user);
    }

    @Override
    public UserEntity authenticate(UserEntity user) {
        Query query = entityManager.createQuery("select user from UserEntity user where user.login = :login and user.password = :password");
        query.setParameter("login", user);
        query.setParameter("password", user);

        return (UserEntity) query.getSingleResult();
    }

    @Override
    public UserEntity getById(Integer id) {
        return entityManager.find(UserEntity.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("select user from UserEntity user").getResultList();
    }
}
