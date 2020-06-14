package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.SeminarVo;
import edu.qingtai.poandse.domain.SeminarVoDetail;
import edu.qingtai.poandse.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/seminar")
public class SeminarController {
    private SeminarService seminarService;

    @Autowired
    public SeminarController(final SeminarService seminarService){
        this.seminarService = seminarService;
    }

    @GetMapping
    public List<SeminarVoDetail> getSeminars(@RequestParam("pageIndex") int pageIndex,
                                       @RequestParam("rd3session") String rd3session){
        return seminarService.queryTrueSeminar(pageIndex, rd3session);
    }

    @GetMapping(value = "/content")
    public SeminarVoDetail getSeminarContent(@RequestParam("uuid") String uuid,
                                             @RequestParam("rd3session") String rd3session){
        return seminarService.queryContent(uuid, rd3session);
    }
}

