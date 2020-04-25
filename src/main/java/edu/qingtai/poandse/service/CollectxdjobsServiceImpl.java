package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Collectxdjobs;
import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsVo;
import edu.qingtai.poandse.mapper.CollectxdjobsMapper;
import edu.qingtai.poandse.mapper.XdjobsMapper;
import edu.qingtai.poandse.util.RedisUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectxdjobsServiceImpl implements CollectxdjobsService{
    private CollectxdjobsMapper collectxdjobsMapper;
    private XdjobsMapper xdjobsMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public CollectxdjobsServiceImpl(final CollectxdjobsMapper collectxdjobsMapper,
                                    final XdjobsMapper xdjobsMapper,
                                    final RedisUtils redisUtils,
                                    final Mapper mapper){
        this.collectxdjobsMapper = collectxdjobsMapper;
        this.xdjobsMapper = xdjobsMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
    }

    @Override
    public void saveXdjobs(String uuid, String rd3session){
        Collectxdjobs collectxdjobs = new Collectxdjobs();
        collectxdjobs.setOpenid(redisUtils.get(rd3session));
        collectxdjobs.setUuid(uuid);
        collectxdjobsMapper.insert(collectxdjobs);
        Xdjobs xdjobs = xdjobsMapper.selectByPrimaryKey(uuid);
        xdjobs.setFavorite(xdjobs.getFavorite() + 1);
        xdjobsMapper.updateByPrimaryKey(xdjobs);
    }

    @Override
    public List<XdjobsVo> queryXdjobsFromOpenid(String rd3session){
        List<Xdjobs> xdjobsList = xdjobsMapper.selectXdjobsByUuidList(
                collectxdjobsMapper.selectUuidByOpenid(redisUtils.get(rd3session)));
        List<XdjobsVo> xdjobsVoList = new ArrayList<>();

        if(xdjobsList == null){
            return xdjobsVoList;
        }else{
            for (Xdjobs xdjobs: xdjobsList) {
                XdjobsVo xdjobsVo = mapper.map(xdjobs, XdjobsVo.class);
                xdjobsVo.setCollect(Boolean.TRUE);
                xdjobsVoList.add(xdjobsVo);
            }
            return xdjobsVoList;
        }
    }

    @Override
    public void deleteXdjobs(String uuid, String rd3session){
        collectxdjobsMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Xdjobs xdjobs = xdjobsMapper.selectByPrimaryKey(uuid);
        xdjobs.setFavorite(xdjobs.getFavorite() - 1);
        xdjobsMapper.updateByPrimaryKey(xdjobs);
    }
}
