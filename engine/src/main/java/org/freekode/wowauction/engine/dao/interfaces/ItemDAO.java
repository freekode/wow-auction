package org.freekode.wowauction.engine.dao.interfaces;


import org.freekode.wowauction.persistence.models.Item;

import java.util.List;

public interface ItemDAO {
    Item save(Item item);

    Item getById(Integer id);

    List<Item> findAll();

    Item isExistsByConstraint(Item item);
}
