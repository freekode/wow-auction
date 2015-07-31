package org.freekode.wowauction.controllers;


import org.freekode.wowauction.dao.interfaces.RealmDAO;
import org.freekode.wowauction.models.Realm;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testMethod() {
        List<Realm> realms = realmDAO.getAll();

        return new ModelAndView("index", "realms", realms);
    }
}
