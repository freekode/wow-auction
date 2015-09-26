package org.freekode.wowauction.updater.beans.interfaces;


import org.freekode.wowauction.persistence.models.Item;

import java.util.List;
import java.util.Set;

public interface ItemBean {
    Set<Item> updateOrCreateAll(Set<Item> items);
}
