package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;

import java.util.List;

public interface CollectseminarService {
    void saveSeminar(String uuid, String rd3session);
    List<SeminarVo> querySeminarFromOpenid(String rd3session);
    void deleteSeminar(String uuid, String rd3session);
}
