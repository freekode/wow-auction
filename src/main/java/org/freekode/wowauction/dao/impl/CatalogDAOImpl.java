package org.freekode.wowauction.dao.impl;

import org.freekode.wowauction.dao.interfaces.CatalogDAO;
import org.freekode.wowauction.models.CatalogEntryEntity;
import org.freekode.wowauction.tools.Utils;
import org.freekode.wowauction.transfer.CatalogEntry;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class CatalogDAOImpl implements CatalogDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<CatalogEntry> getTypes(int headId) {
        Query query = entityManager.createQuery("select e from CatalogEntryEntity e where e.head.id = :headId");
        query.setParameter("headId", headId);

        List<CatalogEntryEntity> entities = query.getResultList();
        List<CatalogEntry> list = new ArrayList<>();
        for (CatalogEntryEntity entity : entities) {
            list.add(new CatalogEntry(entity));
        }
        Utils.initCollection(list, null);

        return list;
    }

    @Override
    public CatalogEntry getByCodeInt(int headId, int codeInteger) {
        Query query = entityManager.createQuery("select e from CatalogEntryEntity e where e.head.id = :headId and e.codeInteger = :codeInteger");
        query.setParameter("headId", headId);
        query.setParameter("codeInteger", codeInteger);

        List<CatalogEntryEntity> entities = query.getResultList();
        if (!entities.isEmpty()) {
            return new CatalogEntry(entities.get(0));
        }

        return null;
    }
}

