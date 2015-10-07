package org.freekode.wowauction.engine.beans.impl;

import org.freekode.wowauction.engine.beans.interfaces.ItemBean;
import org.freekode.wowauction.engine.dao.interfaces.ItemDAO;
import org.freekode.wowauction.engine.transfer.Item;
import org.freekode.wowauction.persistence.models.ItemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.List;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;

    @Override
    public ItemEntity save(ItemEntity item) {
        return itemDAO.save(item);
    }

    @Override
    public ItemEntity getEntity(Integer id) {
        return itemDAO.getEntity(id);
    }

    @Override
    public List<Item> getList(Integer page, Integer amount, Set options) {
        return itemDAO.getList(page, amount, options);
    }
}
