package org.freekode.wowauction.engine.controllers.api;

import org.freekode.wowauction.engine.controllers.data.ResponseData;
import org.freekode.wowauction.engine.controllers.service.SnapshotService;
import org.freekode.wowauction.engine.controllers.data.SnapshotData;
import org.freekode.wowauction.engine.transfer.Snapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/snapshot")
public class SnapshotApiController {
    @Autowired
    private SnapshotService snapshotService;


    @RequestMapping(value = "/24h/{realmId}")
    public @ResponseBody ResponseData<List<SnapshotData>> getSnapshots24h(@PathVariable Integer realmId) {
        return new ResponseData<>(snapshotService.get24h(realmId));
    }
}
