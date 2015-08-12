package org.freekode.wowauction.beans.interfaces;

import org.freekode.wowauction.models.Item;

import java.util.Set;

public interface ItemBean {
    Set<Item> updateOrCreateAll(Set<Item> items);
}
