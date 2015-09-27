package org.freekode.wowauction.engine.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.engine.data.SnapshotData;
import org.freekode.wowauction.engine.transfer.Snapshot;
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
    private SnapshotBean snapshotBean;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView testMethod() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        List<Snapshot> snapshots = snapshotBean.findByToday(null);
        List<SnapshotData> snapshotData = new ArrayList<>();
        for (Snapshot snapshot : snapshots) {
            snapshotData.add(new SnapshotData(snapshot));
        }

        return new ModelAndView("index", "snapshots", mapper.writeValueAsString(snapshotData));
    }
}
