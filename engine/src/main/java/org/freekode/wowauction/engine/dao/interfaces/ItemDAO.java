package org.freekode.wowauction.engine.dao.interfaces;

import org.freekode.wowauction.models.Item;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemDAO {
    Item save(Item item);

    Item getById(Integer id);

    List<Item> findAll();

    Item isExistsByConstraint(Item item);
}
