package edu.qingtai.poandse.mapper;

import edu.qingtai.poandse.domain.Collectseminar;
import edu.qingtai.poandse.domain.CollectseminarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectseminarMapper {
    long countByExample(CollectseminarExample example);

    int deleteByExample(CollectseminarExample example);

    int deleteByPrimaryKey(@Param("uuid") String uuid, @Param("openid") String openid);

    int insert(Collectseminar record);

    int insertSelective(Collectseminar record);

    List<Collectseminar> selectByExample(CollectseminarExample example);

    int updateByExampleSelective(@Param("record") Collectseminar record, @Param("example") CollectseminarExample example);

    int updateByExample(@Param("record") Collectseminar record, @Param("example") CollectseminarExample example);

    List<String> selectUuidByOpenid(@Param("openid") String openid);
}