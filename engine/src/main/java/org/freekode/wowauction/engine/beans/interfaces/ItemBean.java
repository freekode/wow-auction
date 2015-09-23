package org.freekode.wowauction.engine.beans.interfaces;

import org.freekode.wowauction.models.Item;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    Item save(Item item);

    Item getById(Integer id);

    List<Item> findAll();

    Set<Item> updateOrCreateAll(Set<Item> items);
}
