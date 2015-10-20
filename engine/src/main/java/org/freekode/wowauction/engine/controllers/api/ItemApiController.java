package org.freekode.wowauction.engine.controllers.api;

import org.freekode.wowauction.engine.controllers.data.ItemData;
import org.freekode.wowauction.engine.controllers.data.ResponseData;
import org.freekode.wowauction.engine.controllers.service.ItemService;
import org.freekode.wowauction.engine.controllers.service.SnapshotService;
import org.freekode.wowauction.engine.controllers.data.SnapshotData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/item")
public class ItemApiController {
    @Autowired
    private ItemService itemService;


    @RequestMapping(value = "/list/{page}/{amount}")
    public @ResponseBody ResponseData<List<ItemData>> getSnapshots24h(@PathVariable Integer page,
                                                                      @PathVariable Integer amount) {

        return new ResponseData<>(itemService.getList(page, amount));
    }
}
