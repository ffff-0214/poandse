package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;
import edu.qingtai.poandse.domain.XdjobsVoDetail;

import java.util.List;

public interface XdjobsService {
    List<Xdjobs> queryPageJobs(int pageIndex);
    List<XdjobsVo> queryTrueXdjobs(int pageIndex, String rd3session);
    XdjobsVoDetail queryContent(String uuid, String rd3session);
}
