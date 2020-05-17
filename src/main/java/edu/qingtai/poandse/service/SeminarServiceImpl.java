package edu.qingtai.poandse.service;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarVo;
import edu.qingtai.poandse.domain.SeminarVoDetail;
import edu.qingtai.poandse.mapper.CollectseminarMapper;
import edu.qingtai.poandse.mapper.SeminarMapper;
import edu.qingtai.poandse.util.ConstData;
import edu.qingtai.poandse.util.RedisUtils;
import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SeminarServiceImpl implements SeminarService{
    private SeminarMapper seminarMapper;
    private CollectseminarMapper collectseminarMapper;
    private RedisUtils redisUtils;
    private Mapper mapper;

    @Autowired
    public SeminarServiceImpl(final SeminarMapper seminarMapper,
                              final CollectseminarMapper collectseminarMapper,
                              final RedisUtils redisUtils,
                              final Mapper mapper){
        this.seminarMapper = seminarMapper;
        this.collectseminarMapper = collectseminarMapper;
        this.redisUtils = redisUtils;
        this.mapper = mapper;
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

    @Override
    public SeminarVoDetail queryContent(String uuid, String rd3session){
        List<String> uuidList = collectseminarMapper.selectUuidByOpenid(redisUtils.get(rd3session));
        Seminar seminar = seminarMapper.selectByPrimaryKey(uuid);
        SeminarVoDetail seminarVoDetail = mapper.map(seminar, SeminarVoDetail.class);
        if(uuidList.contains(uuid)){
            seminarVoDetail.setCollect(Boolean.TRUE);
            return seminarVoDetail;
        }else{
            return seminarVoDetail;
        }
    }

    @Override
    public List<SeminarVo> queryTrueSeminar(int pageIndex, String rd3session){
//        Mapper mapper = DozerBeanMapperBuilder.buildDefault();

        List<Seminar> seminarList =  queryPageSeminars(pageIndex);

        List<String> uuidList = collectseminarMapper.selectUuidByOpenid(redisUtils.get(rd3session));

//        int size = seminarList.size();

        List<SeminarVo> seminarVoList = new ArrayList<>();

        if(seminarList == null){
            return seminarVoList;
        }else{
            for (Seminar seminar: seminarList) {
                if(uuidList.contains(seminar.getUuid())){
                    SeminarVo seminarVo = mapper.map(seminar, SeminarVo.class);
                    seminarVo.setCollect(Boolean.TRUE);
                    seminarVoList.add(seminarVo);
                }else{
                    seminarVoList.add(mapper.map(seminar, SeminarVo.class));
                }
            }
            return seminarVoList;
        }



//        for(int i = 0; i < size; i++){
//            if(uuidList.contains(seminarList.get(i).getUuid())){
//                SeminarVo seminarVo = mapper.map(seminarList.get(i), SeminarVo.class);
//                seminarVo.setCollect(Boolean.TRUE);
//                seminarVoList.add(seminarVo);
//            }else{
//                seminarVoList.add(mapper.map(seminarList.get(i), SeminarVo.class));
//            }
//        }
    }
}
