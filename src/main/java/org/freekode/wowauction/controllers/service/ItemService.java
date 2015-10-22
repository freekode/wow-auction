package org.freekode.wowauction.controllers.service;

import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.controllers.data.ItemData;
import org.freekode.wowauction.transfer.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemService {
    @Autowired
    private ItemBean itemBean;


    public List<ItemData> getList(Integer page, Integer amount) {
        List<ItemData> items = new ArrayList<>();
        for (Item item : itemBean.getList(page, amount, null)) {
            items.add(new ItemData(item));
        }

        return items;
    }
}
