package org.freekode.wowauction.controllers;


import org.freekode.wowauction.dao.interfaces.SnapshotDAO;
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
    private SnapshotDAO snapshotDAO;
    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testMethod() {
        List<Snapshot> snapshots = snapshotDAO.findByToday();

        return new ModelAndView("index", "snapshots", snapshots);
    }
}
