package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.service.CollectxdjobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/collectXdjobs")
public class CollectxdjobsController {
    private CollectxdjobsService collectxdjobsService;

    @Autowired
    public CollectxdjobsController(final CollectxdjobsService collectxdjobsService){
        this.collectxdjobsService = collectxdjobsService;
    }

    @PostMapping
    public void saveXdjobsCollect(@RequestParam("uuid") String uuid,
                             @RequestParam("rd3session") String rd3session){
        collectxdjobsService.saveXdjobs(uuid, rd3session);
    }

    @GetMapping
    public List<Xdjobs> queryXdjobsByone(@RequestParam("rd3session") String rd3session){
        return collectxdjobsService.queryXdjobsFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteXdjobsCollect(@RequestParam("uuid") String uuid,
                                     @RequestParam("rd3session") String rd3session){
        collectxdjobsService.deleteXdjobs(uuid, rd3session);
    }
}
