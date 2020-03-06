package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;

import java.util.List;

public interface XdjobsService {
    List<Xdjobs> queryPageJobs(int pageIndex);
}
