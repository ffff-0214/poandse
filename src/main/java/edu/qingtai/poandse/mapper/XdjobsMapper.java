package edu.qingtai.poandse.mapper;

import edu.qingtai.poandse.domain.Xdjobs;
import edu.qingtai.poandse.domain.XdjobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface XdjobsMapper {
    long countByExample(XdjobsExample example);

    int deleteByExample(XdjobsExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Xdjobs record);

    int insertSelective(Xdjobs record);

    List<Xdjobs> selectByExampleWithBLOBs(XdjobsExample example);

    List<Xdjobs> selectByExample(XdjobsExample example);

    Xdjobs selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Xdjobs record, @Param("example") XdjobsExample example);

    int updateByExampleWithBLOBs(@Param("record") Xdjobs record, @Param("example") XdjobsExample example);

    int updateByExample(@Param("record") Xdjobs record, @Param("example") XdjobsExample example);

    int updateByPrimaryKeySelective(Xdjobs record);

    int updateByPrimaryKeyWithBLOBs(Xdjobs record);

    int updateByPrimaryKey(Xdjobs record);

    List<String> selectUuidByDateDesc(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<Xdjobs> selectXdjobsByUuidList(@Param("uuidList") List<String> uuidList);
}