package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;
import edu.qingtai.poandse.service.XdjobsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/xdjobs")
public class XdjobsController {
    private XdjobsService xdjobsService;

    @Autowired
    public XdjobsController(final XdjobsService xdjobsService){
        this.xdjobsService = xdjobsService;
    }

    @GetMapping
    public List<XdjobsVo> getXdjobs(@RequestParam("pageIndex") int pageIndex,
                                    @RequestParam("rd3session") String rd3session){
        return xdjobsService.queryTrueXdjobs(pageIndex, rd3session);
    }


}
