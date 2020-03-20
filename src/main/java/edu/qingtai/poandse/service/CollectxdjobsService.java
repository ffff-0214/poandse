package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;

import java.util.List;

public interface CollectxdjobsService {
    void saveXdjobs(String uuid, String rd3session);
    List<Xdjobs> queryXdjobsFromOpenid(String rd3session);
}
