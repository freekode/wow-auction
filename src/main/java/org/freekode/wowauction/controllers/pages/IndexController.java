package org.freekode.wowauction.controllers.pages;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.freekode.wowauction.beans.interfaces.RealmBean;
import org.freekode.wowauction.controllers.data.RealmData;
import org.freekode.wowauction.transfer.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RealmBean realmBean;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView indexPage() throws JsonProcessingException {
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
    public ModelAndView itemsPage() throws JsonProcessingException {
        return new ModelAndView("items");
    }
}
