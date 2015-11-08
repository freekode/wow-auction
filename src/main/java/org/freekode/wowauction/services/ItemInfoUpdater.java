package org.freekode.wowauction.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.dao.interfaces.ConstantDAO;
import org.freekode.wowauction.models.ItemEntity;
import org.freekode.wowauction.models.ItemInfoEntity;
import org.freekode.wowauction.tools.ConstantKeys;
import org.freekode.wowauction.tools.WorldOfWarcraftAPI;
import org.freekode.wowauction.transfer.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class ItemInfoUpdater {
    private static final Logger logger = LogManager.getLogger(ItemInfoUpdater.class);

    @Autowired
    private ItemBean itemBean;

    @Autowired
    private ConstantDAO constantDAO;


    public void scheduleUpdate() {
        updateItemsInfo();
    }

    public void updateItemsInfo() {
        // update info for all items which do not have it
        logger.info("update item info started");

        String apiKey = constantDAO.getByName(ConstantKeys.WOW_API_KEY);
        List<Item> items = itemBean.findAll(null);
        List<ItemEntity> updateItems = new ArrayList<>();


        logger.info("get info about new items");
        for (Item item : items) {
            if (item.getItemInfo() != null) {
                continue;
            }

            Map<String, Object> infoMap = WorldOfWarcraftAPI.getItemInfo("eu", item.getIdentifier(), apiKey);
            if (infoMap == null) {
                logger.error("can not receive item info for item = " + item.getIdentifier());
                continue;
            }

            ItemEntity itemEntity = item.getEntity();
            ItemInfoEntity info = itemBean.buildItemInfo(infoMap.get("name").toString(),
                    new Integer(infoMap.get("itemLevel").toString()), infoMap.get("icon") + ".jpg",
                    new Integer(infoMap.get("quality").toString()), new Integer(infoMap.get("itemClass").toString()),
                    new Integer(infoMap.get("itemSubclass").toString()),
                    new Integer(infoMap.get("inventoryType").toString()), new Long(infoMap.get("sellPrice").toString()), itemEntity);

            itemEntity.setItemInfo(info);

            updateItems.add(itemEntity);
        }

        logger.info("save items information = " + updateItems.size());
        itemBean.updateAll(updateItems);


        logger.info("update item info ended");
    }
}
