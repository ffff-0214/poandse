package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Collectxdjobs;
import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.mapper.CollectxdjobsMapper;
import edu.qingtai.poandse.mapper.XdjobsMapper;
import edu.qingtai.poandse.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectxdjobsServiceImpl implements CollectxdjobsService{
    private CollectxdjobsMapper collectxdjobsMapper;
    private XdjobsMapper xdjobsMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectxdjobsServiceImpl(final CollectxdjobsMapper collectxdjobsMapper,
                                    final XdjobsMapper xdjobsMapper,
                                    final RedisUtils redisUtils){
        this.collectxdjobsMapper = collectxdjobsMapper;
        this.xdjobsMapper = xdjobsMapper;
        this.redisUtils = redisUtils;
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
    public List<Xdjobs> queryXdjobsFromOpenid(String rd3session){
        return xdjobsMapper.selectXdjobsByUuidList(
                collectxdjobsMapper.selectUuidByOpenid(redisUtils.get(rd3session)));
    }

    @Override
    public void deleteXdjobs(String uuid, String rd3session){
        collectxdjobsMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Xdjobs xdjobs = xdjobsMapper.selectByPrimaryKey(uuid);
        xdjobs.setFavorite(xdjobs.getFavorite() - 1);
        xdjobsMapper.updateByPrimaryKey(xdjobs);
    }
}
