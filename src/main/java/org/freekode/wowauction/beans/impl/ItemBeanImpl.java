package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.CatalogBean;
import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.models.ItemInfoEntity;
import org.freekode.wowauction.transfer.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class ItemBeanImpl implements ItemBean {
    @Autowired
    private ItemDAO itemDAO;

    @Autowired
    private CatalogBean catalogBean;


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

    @Override
    public List<ItemEntity> createAll(List<ItemEntity> items) {
        return itemDAO.createAll(items);
    }

    @Override
    public List<ItemEntity> updateAll(List<ItemEntity> items) {
        return itemDAO.updateAll(items);
    }

    @Override
    public ItemInfoEntity buildItemInfo(String name, String level, String url, String icon,
                                        Integer quality, Integer itemClass, Integer itemSubclass, Integer inventorySlot,
                                        ItemEntity item) {
        ItemInfoEntity info = new ItemInfoEntity();

        info.setName(name);
        info.setLevel(level);
        info.setUrl(url);
        info.setIcon(icon);
        info.setItem(item);
        info.setQuality(catalogBean.getQualityType(quality).getEntity());
        info.setItemClass(catalogBean.getItemClassType(itemClass).getEntity());
        info.setItemSubclass(catalogBean.getItemSubclassType(itemSubclass).getEntity());
        info.setInventorySlot(catalogBean.getItemInventorySlotType(inventorySlot).getEntity());

        return info;
    }
}
