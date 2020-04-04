package edu.qingtai.poandse.service;



import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;

import java.util.List;

public interface SeminarService {
    List<Seminar> queryPageSeminars(int pageIndex);
    List<SeminarVo> queryTrueSeminar(int pageIndex, String rd3session);
}
