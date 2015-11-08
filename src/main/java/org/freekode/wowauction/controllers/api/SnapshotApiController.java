package org.freekode.wowauction.controllers.api;

import org.freekode.wowauction.controllers.data.ResponseData;
import org.freekode.wowauction.controllers.data.SnapshotData;
import org.freekode.wowauction.controllers.service.SnapshotService;
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


    @RequestMapping(value = "/24h/realm/{realmId}")
    public
    @ResponseBody
    ResponseData<List<SnapshotData>> getSnapshots24h(@PathVariable Integer realmId) {
        return new ResponseData<>(snapshotService.get24h(realmId));
    }

    @RequestMapping(value = "/realm/{realmId}")
    public
    @ResponseBody
    ResponseData<List<SnapshotData>> getAllSnapshots(@PathVariable Integer realmId) {
        return new ResponseData<>(snapshotService.findByRealm(realmId));
    }
}
