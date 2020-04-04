package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;

import java.util.List;

public interface XdjobsService {
    List<Xdjobs> queryPageJobs(int pageIndex);
    List<XdjobsVo> queryTrueXdjobs(int pageIndex, String rd3session);
}
