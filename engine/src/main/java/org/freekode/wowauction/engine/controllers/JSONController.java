package org.freekode.wowauction.engine.controllers;

import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.persistence.models.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anna on 10/08/15.
 */
@Controller
@RequestMapping("/models")
public class JSONController {
    @Autowired
    private SnapshotBean snapshotBean;


    @RequestMapping(value = "/snapshots/{id}", method = RequestMethod.GET)
    public
    @ResponseBody
    Snapshot getSnapshotByIdInJSON(@PathVariable Integer id) {
        return snapshotBean.getById(id);
    }

    @RequestMapping(value = "/snapshots", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Snapshot> getSnapshotsListInJSON() {
        return snapshotBean.findAll();
    }

    @RequestMapping(value = "/snapshots/today")
    public
    @ResponseBody
    List<Snapshot> getSnapshotsTodayInJSON() {
        return snapshotBean.findByToday();
    }

    @RequestMapping(value = "/snapshots/24h")
    public
    @ResponseBody
    List<Snapshot> getSnapshots24h() {
        Date todayStart, todayEnd;
        Calendar today = Calendar.getInstance();

        todayEnd = today.getTime();

        today.add(Calendar.DAY_OF_MONTH, -1);
        todayStart = today.getTime();

        return snapshotBean.findBetweenDates(todayStart, todayEnd);
    }
}
