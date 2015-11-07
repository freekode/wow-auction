package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.ConstantDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class ConstantDAOImpl implements ConstantDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @SuppressWarnings("unchecked")
    @Override
    public String getByName(String name) {
        Query query = entityManager.createNativeQuery("select c.value from constants c where c.name = '" + name + "'");
        List<String> list  = query.getResultList();

        if (!list.isEmpty()) {
            return list.get(0);
        }

        return null;
    }
}
