package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;

import java.util.List;

public interface CollectxdjobsService {
    void saveXdjobs(String uuid, String rd3session);
    List<XdjobsVo> queryXdjobsFromOpenid(String rd3session);
    void deleteXdjobs(String uuid, String rd3session);
}
