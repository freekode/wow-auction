package org.freekode.wowauction.controllers;


import org.freekode.wowauction.beans.interfaces.ItemBean;
import org.freekode.wowauction.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Item;
import org.freekode.wowauction.models.Realm;
import org.freekode.wowauction.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private RealmDAO realmDAO;

    @Autowired
    private ItemBean itemBean;

    @Autowired
    private SnapshotBean snapshotBean;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testMethod() {
        List<Realm> realms = realmDAO.findAll();

        Item item0 = new Item();
        item0.setIdentifier("112");
        item0.setUniqueId("112");
        itemBean.save(item0);

        Snapshot snapshot = new Snapshot();
        snapshot.setFile("zzz");
        snapshotBean.save(snapshot);

        return new ModelAndView("index", "realms", realms);
    }
}
