package org.freekode.wowauction.engine.controllers.api;

import org.freekode.wowauction.engine.beans.interfaces.SnapshotBean;
import org.freekode.wowauction.engine.transfer.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api/snapshot")
public class SnapshotApiController {
    @Autowired
    private SnapshotBean snapshotBean;


    @RequestMapping(value = "/24h/{realmId}")
    public @ResponseBody List<Snapshot> getSnapshots24h(@PathVariable Integer realmId) {
        Date todayStart, todayEnd;
        Calendar today = Calendar.getInstance();

        todayEnd = today.getTime();

        today.add(Calendar.DAY_OF_MONTH, -1);
        todayStart = today.getTime();

        return snapshotBean.findBetweenDates(realmId, todayStart, todayEnd, null);
    }
}
