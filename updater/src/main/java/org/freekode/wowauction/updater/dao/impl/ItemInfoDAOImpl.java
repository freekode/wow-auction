package org.freekode.wowauction.updater.dao.impl;

import org.freekode.wowauction.persistence.models.ItemEntity;
import org.freekode.wowauction.persistence.models.ItemInfoEntity;
import org.freekode.wowauction.updater.dao.interfaces.ItemInfoDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class ItemInfoDAOImpl implements ItemInfoDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public ItemInfoEntity buildInfo(ItemEntity item, String name, String level, String url, String icon) {
        ItemInfoEntity info = new ItemInfoEntity();
        info.setName(name);
        info.setLevel(level);
        info.setUrl(url);
        info.setIcon("http://wow.zamimg.com/images/wow/icons/large/" + icon + ".jpg");
        info.setItem(item);
        return info;
    }

    @Override
    public List<ItemInfoEntity> saveAll(List<ItemInfoEntity> items) {
        List<ItemInfoEntity> updateItems = new ArrayList<>();
        for (ItemInfoEntity itemInfo : items) {
            updateItems.add(entityManager.merge(itemInfo));
        }


        return updateItems;
    }
}
