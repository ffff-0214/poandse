package edu.qingtai.poandse.mapper;

import edu.qingtai.poandse.domain.Collectxdjobs;
import edu.qingtai.poandse.domain.CollectxdjobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectxdjobsMapper {
    long countByExample(CollectxdjobsExample example);

    int deleteByExample(CollectxdjobsExample example);

    int deleteByPrimaryKey(@Param("uuid") String uuid, @Param("openid") String openid);

    int insert(Collectxdjobs record);

    int insertSelective(Collectxdjobs record);

    List<Collectxdjobs> selectByExample(CollectxdjobsExample example);

    int updateByExampleSelective(@Param("record") Collectxdjobs record, @Param("example") CollectxdjobsExample example);

    int updateByExample(@Param("record") Collectxdjobs record, @Param("example") CollectxdjobsExample example);

    List<String> selectUuidByOpenid(@Param("openid") String openid);
}