package org.freekode.wowauction.beans.interfaces;


import org.freekode.wowauction.transfer.Item;
import org.freekode.wowauction.persistence.models.ItemEntity;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    ItemEntity save(ItemEntity item);

    ItemEntity getEntity(Integer id);

    List<Item> getList(Integer page, Integer amount, Set options);
}
