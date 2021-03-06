package org.freekode.wowauction.beans.impl;

import org.freekode.wowauction.beans.interfaces.CatalogBean;
import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.dao.interfaces.ItemDAO;
import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.models.ItemInfoEntity;
import org.freekode.wowauction.transfer.CatalogEntry;
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
    public Item getByIdentifier(String identifier, Set options) {
        List<Item> items = findItem(identifier, null, null, null, options);

        if (!items.isEmpty()) {
            return items.get(0);
        }

        return null;
    }

    @Override
    public List<Item> findAll(Set options) {
        return itemDAO.findItem(null, null, null, null, options);
    }

    @Override
    public List<Item> findItem(String identifier, String rand, String seed, String context, Set options) {
        return itemDAO.findItem(identifier, rand, seed, context, options);
    }

    @Override
    public ItemInfoEntity buildItemInfo(String name, Integer level, String icon, Integer quality, Integer itemClass,
                                        Integer itemSubclass, Integer inventorySlot, Long sellPrice,
                                        ItemEntity item) {
        ItemInfoEntity info = new ItemInfoEntity();

        info.setName(name);
        info.setItemLevel(level);
        info.setIcon(icon);
        info.setItem(item);

        if (sellPrice > 0) {
            info.setSellPrice(sellPrice);
        }

        CatalogEntry itemQuality = catalogBean.getQualityType(quality);
        if (itemQuality != null) {
            info.setQuality(itemQuality.getEntity());
        }

        CatalogEntry itemClassEntry = catalogBean.getItemClassType(itemClass);
        if (itemClassEntry != null) {
            info.setItemClass(itemClassEntry.getEntity());
        }

        CatalogEntry subclassEntry = catalogBean.getItemSubclassType(itemSubclass);
        if (subclassEntry != null) {
            info.setItemSubclass(catalogBean.getItemSubclassType(itemSubclass).getEntity());
        }

        CatalogEntry inventorySlotEntry = catalogBean.getItemInventorySlotType(inventorySlot);
        if (inventorySlotEntry != null) {
            info.setInventorySlot(inventorySlotEntry.getEntity());
        }

        return info;
    }
}
