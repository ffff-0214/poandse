package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Collectseminar;
import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.mapper.CollectseminarMapper;
import edu.qingtai.poandse.mapper.SeminarMapper;
import edu.qingtai.poandse.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectseminarServiceImpl implements CollectseminarService{
    private CollectseminarMapper collectseminarMapper;
    private SeminarMapper seminarMapper;
    private RedisUtils redisUtils;

    @Autowired
    public CollectseminarServiceImpl(final CollectseminarMapper collectseminarMapper,
                                     final SeminarMapper seminarMapper,
                                     final RedisUtils redisUtils){
        this.collectseminarMapper = collectseminarMapper;
        this.seminarMapper = seminarMapper;
        this.redisUtils = redisUtils;
    }

    @Override
    public void saveSeminar(String uuid, String rd3session){
        String openid = redisUtils.get(rd3session);
        Collectseminar collectseminar = new Collectseminar();
        collectseminar.setOpenid(openid);
        collectseminar.setUuid(uuid);
        collectseminarMapper.insert(collectseminar);
        Seminar seminar = seminarMapper.selectByPrimaryKey(uuid);
        seminar.setFavorite(seminar.getFavorite() + 1);
        seminarMapper.updateByPrimaryKey(seminar);
    }

    @Override
    public List<Seminar> querySeminarFromOpenid(String rd3session){
        return seminarMapper.selectSeminarsByUuidList(
                collectseminarMapper.selectUuidByOpenid(redisUtils.get(rd3session)));
    }

    @Override
    public void deleteSeminar(String uuid, String rd3session){
        collectseminarMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Seminar seminar = seminarMapper.selectByPrimaryKey(uuid);
        seminar.setFavorite(seminar.getFavorite() - 1);
        seminarMapper.updateByPrimaryKey(seminar);
    }
}
