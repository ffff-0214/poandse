package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Collectseminar;
import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;
import edu.qingtai.poandse.mapper.CollectseminarMapper;
import edu.qingtai.poandse.mapper.SeminarMapper;
import edu.qingtai.poandse.util.RedisUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CollectseminarServiceImpl implements CollectseminarService{
    private CollectseminarMapper collectseminarMapper;
    private SeminarMapper seminarMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public CollectseminarServiceImpl(final CollectseminarMapper collectseminarMapper,
                                     final SeminarMapper seminarMapper,
                                     final RedisUtils redisUtils,
                                     final Mapper mapper){
        this.collectseminarMapper = collectseminarMapper;
        this.seminarMapper = seminarMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
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
    public List<SeminarVo> querySeminarFromOpenid(String rd3session){
        List<Seminar> seminarList = seminarMapper.selectSeminarsByUuidList(
                collectseminarMapper.selectUuidByOpenid(redisUtils.get(rd3session)));
        List<SeminarVo> seminarVoList = new ArrayList<>();

        if(seminarList == null){
            return seminarVoList;
        }else{
            for (Seminar seminar: seminarList) {
                SeminarVo seminarVo = mapper.map(seminar, SeminarVo.class);
                seminarVo.setCollect(Boolean.TRUE);
                seminarVoList.add(seminarVo);
            }
            return seminarVoList;
        }
    }

    @Override
    public void deleteSeminar(String uuid, String rd3session){
        collectseminarMapper.deleteByPrimaryKey(uuid, redisUtils.get(rd3session));
        Seminar seminar = seminarMapper.selectByPrimaryKey(uuid);
        seminar.setFavorite(seminar.getFavorite() - 1);
        seminarMapper.updateByPrimaryKey(seminar);
    }
}
