package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;
import org.freekode.wowauction.persistence.models.ItemInfoEntity;

import java.util.List;

public interface ItemInfoDAO {
    ItemInfoEntity buildInfo(ItemEntity item, String name, String level, String link, String icon);

    List<ItemInfoEntity> saveAll(List<ItemInfoEntity> items);
}
