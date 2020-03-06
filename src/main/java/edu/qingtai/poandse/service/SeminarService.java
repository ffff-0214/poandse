package edu.qingtai.poandse.service;



import edu.qingtai.poandse.domain.Seminar;

import java.util.List;

public interface SeminarService {
    List<Seminar> queryPageSeminars(int pageIndex);
}
