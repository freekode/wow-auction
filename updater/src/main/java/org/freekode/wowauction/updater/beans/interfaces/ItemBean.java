package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    List<ItemEntity> saveAll(List<ItemEntity> items);
}
