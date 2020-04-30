package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;
import edu.qingtai.poandse.service.CollectseminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/collectSeminar")
public class CollectseminarController {
    private CollectseminarService collectseminarService;

    @Autowired
    public CollectseminarController(final CollectseminarService collectseminarService){
        this.collectseminarService = collectseminarService;
    }

    @PostMapping
    public void saveSeminarCollect(@RequestBody Map<String,String> info){
        collectseminarService.saveSeminar(info.get("uuid"), info.get("rd3session"));
    }

    @GetMapping
    public List<SeminarVo> querySeminarByone(@RequestParam("rd3session") String rd3session){
        return collectseminarService.querySeminarFromOpenid(rd3session);
    }

    @DeleteMapping
    public void deleteSeminarCollect(@RequestBody Map<String,String> info){
        collectseminarService.deleteSeminar(info.get("uuid"), info.get("rd3session"));
    }
}
