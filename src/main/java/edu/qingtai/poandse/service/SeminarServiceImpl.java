package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.mapper.SeminarMapper;
import edu.qingtai.poandse.util.ConstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeminarServiceImpl implements SeminarService{
    private SeminarMapper seminarMapper;

    @Autowired
    public SeminarServiceImpl(final SeminarMapper seminarMapper){
        this.seminarMapper = seminarMapper;
    }

    @Override
    public List<Seminar> queryPageSeminars(int pageIndex){
        //pageIndex从前端传，从1开始
        int startIndex = (pageIndex - 1) * ConstData.pageSize;
        List<String> uuidList = seminarMapper.selectUuidByDateDesc(startIndex, ConstData.pageSize);
        if(uuidList.size() == 0){
            return null;
        }
        return seminarMapper.selectSeminarsByUuidList(uuidList);
    }
}
