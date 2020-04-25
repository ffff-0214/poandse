package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;
import edu.qingtai.poandse.mapper.CollectxdjobsMapper;
import edu.qingtai.poandse.mapper.XdjobsMapper;
import edu.qingtai.poandse.util.ConstData;
import edu.qingtai.poandse.util.RedisUtils;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class XdjobsServiceImpl implements XdjobsService{
    private XdjobsMapper xdjobsMapper;
    private CollectxdjobsMapper collectxdjobsMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public XdjobsServiceImpl(final XdjobsMapper xdjobsMapper,
                             final CollectxdjobsMapper collectxdjobsMapper,
                             final RedisUtils redisUtils,
                             final Mapper mapper){
        this.xdjobsMapper=xdjobsMapper;
        this.collectxdjobsMapper = collectxdjobsMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
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

    @Override
    public List<XdjobsVo> queryTrueXdjobs(int pageIndex, String rd3session){
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        List<Xdjobs> xdjobsList = queryPageJobs(pageIndex);

        List<String> uuidList = collectxdjobsMapper.selectUuidByOpenid(redisUtils.get(rd3session));

        List<XdjobsVo> xdjobsVoList = new ArrayList<>();

        if(xdjobsList == null){
            return xdjobsVoList;
        }else{
            for (Xdjobs xdjobs: xdjobsList) {
                if(uuidList.contains(xdjobs.getUuid())){
                    XdjobsVo xdjobsVo = mapper.map(xdjobs, XdjobsVo.class);
                    xdjobsVo.setCollect(Boolean.TRUE);
                    xdjobsVoList.add(xdjobsVo);
                }else{
                    xdjobsVoList.add(mapper.map(xdjobs, XdjobsVo.class));
                }
            }

            return xdjobsVoList;
        }
    }
}
