package org.freekode.wowauction.dao.interfaces;

import org.freekode.wowauction.models.Item;

import java.util.List;
import java.util.Set;

public interface ItemDAO {
    void create(Item item);

    void createAll(Set<Item> items);

    Item getById(Integer id);

    List<Item> findAll();
}
