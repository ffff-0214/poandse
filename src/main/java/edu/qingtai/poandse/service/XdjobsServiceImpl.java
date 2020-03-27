package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.mapper.XdjobsMapper;
import edu.qingtai.poandse.util.ConstData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XdjobsServiceImpl implements XdjobsService{
    private XdjobsMapper xdjobsMapper;

    @Autowired
    public XdjobsServiceImpl(final XdjobsMapper xdjobsMapper){
        this.xdjobsMapper=xdjobsMapper;
    }

    @Override
    public List<Xdjobs> queryPageJobs(int pageIndex){
        //前端是从第一页开始
        int startIndex = (pageIndex - 1) * ConstData.pageSize;
        List<String> uuidList = xdjobsMapper.selectUuidByDateDesc(startIndex, ConstData.pageSize);
        if(uuidList.size() == 0){
            return null;
        }
        return xdjobsMapper.selectXdjobsByUuidList(uuidList);
    }
}
