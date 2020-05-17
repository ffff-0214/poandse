package edu.qingtai.poandse.service;



import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;
import edu.qingtai.poandse.domain.SeminarVoDetail;

import java.util.List;

public interface SeminarService {
    List<Seminar> queryPageSeminars(int pageIndex);
    List<SeminarVo> queryTrueSeminar(int pageIndex, String rd3session);
    SeminarVoDetail queryContent(String uuid, String rd3session);
}
