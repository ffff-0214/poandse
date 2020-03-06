package edu.qingtai.poandse.controller;

import edu.qingtai.poandse.domain.Seminar;
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
    public List<Seminar> getSeminars(@RequestParam("pageIndex") int pageIndex){
        return seminarService.queryPageSeminars(pageIndex);
    }
}
