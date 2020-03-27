package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.service.CollectseminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/collectSeminar")
public class CollectseminarController {
    private CollectseminarService collectseminarService;

    @Autowired
    public CollectseminarController(final CollectseminarService collectseminarService){
        this.collectseminarService = collectseminarService;
    }

    @PostMapping
    public void saveSeminarCollect(@RequestParam("uuid") String uuid,
                                   @RequestParam("rd3session") String rd3session){
        collectseminarService.saveSeminar(uuid, rd3session);
    }

    @GetMapping
    public List<Seminar> querySeminarByone(@RequestParam("rd3session") String rd3session){
        return collectseminarService.querySeminarFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteSeminarCollect(@RequestParam("uuid") String uuid,
                                     @RequestParam("rd3session") String rd3session){
        collectseminarService.deleteSeminar(uuid, rd3session);
    }
}
