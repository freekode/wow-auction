package org.freekode.wowauction.controllers.pages;


import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.beans.interfaces.RealmBean;
import org.freekode.wowauction.controllers.data.ItemData;
import org.freekode.wowauction.controllers.data.RealmData;
import org.freekode.wowauction.transfer.Item;
import org.freekode.wowauction.transfer.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RealmBean realmBean;

    @Autowired
    private ItemBean itemBean;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() {
        List<Realm> realms = realmBean.findAll(null);
        List<RealmData> realmData = new ArrayList<>();
        for (Realm realm : realms) {
            realmData.add(new RealmData(realm));
        }

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("realms", realmData);

        return modelAndView;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public ModelAndView itemsPage() {
        return new ModelAndView("items");
    }

    @RequestMapping(value = "/item/{identifier}", method = RequestMethod.GET)
    public ModelAndView itemPage(@PathVariable("identifier") String identifier) {
        Item item = itemBean.getByIdentifier(identifier, Collections.singleton(Item.Options.INIT_ITEM_BIDS));

        ItemData itemData = new ItemData();
        if (item != null) {
            itemData = new ItemData(item);
        }

        return new ModelAndView("item", "item", itemData);
    }
}
