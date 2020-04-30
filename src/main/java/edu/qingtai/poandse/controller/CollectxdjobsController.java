package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;
import edu.qingtai.poandse.service.CollectxdjobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collectXdjobs")
public class CollectxdjobsController {
    private CollectxdjobsService collectxdjobsService;

    @Autowired
    public CollectxdjobsController(final CollectxdjobsService collectxdjobsService){
        this.collectxdjobsService = collectxdjobsService;
    }

    @PostMapping
    //    @RequestParam("uuid") String uuid,
//    @RequestParam("rd3session") String rd3session
    public void saveXdjobsCollect(@RequestBody Map<String,String> info){
        collectxdjobsService.saveXdjobs(info.get("uuid"), info.get("rd3session"));
    }

    @GetMapping
    public List<XdjobsVo> queryXdjobsByone(@RequestParam("rd3session") String rd3session){
        return collectxdjobsService.queryXdjobsFromOpenid(rd3session);
    }

    @DeleteMapping
//    @RequestParam("uuid") String uuid,
//    @RequestParam("rd3session") String rd3session
    public void deleteXdjobsCollect(@RequestBody Map<String,String> info){
        collectxdjobsService.deleteXdjobs(info.get("uuid"), info.get("rd3session"));
    }
}
