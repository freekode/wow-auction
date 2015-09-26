package org.freekode.wowauction.updater.dao.interfaces;


import org.freekode.wowauction.persistence.models.Item;

import java.util.List;

public interface ItemDAO {
    Item save(Item item);

    Item isExistsByConstraint(Item item);
}
