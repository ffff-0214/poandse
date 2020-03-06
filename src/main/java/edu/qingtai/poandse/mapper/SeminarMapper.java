package edu.qingtai.poandse.mapper;

import edu.qingtai.poandse.domain.Seminar;
import edu.qingtai.poandse.domain.SeminarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeminarMapper {
    long countByExample(SeminarExample example);

    int deleteByExample(SeminarExample example);

    int deleteByPrimaryKey(String uuid);

    int insert(Seminar record);

    int insertSelective(Seminar record);

    List<Seminar> selectByExampleWithBLOBs(SeminarExample example);

    List<Seminar> selectByExample(SeminarExample example);

    Seminar selectByPrimaryKey(String uuid);

    int updateByExampleSelective(@Param("record") Seminar record, @Param("example") SeminarExample example);

    int updateByExampleWithBLOBs(@Param("record") Seminar record, @Param("example") SeminarExample example);

    int updateByExample(@Param("record") Seminar record, @Param("example") SeminarExample example);

    int updateByPrimaryKeySelective(Seminar record);

    int updateByPrimaryKeyWithBLOBs(Seminar record);

    int updateByPrimaryKey(Seminar record);

    List<String> selectUuidByDateDesc(@Param("startIndex") int startIndex, @Param("pageSize") int pageSize);

    List<Seminar> selectSeminarsByUuidList(@Param("uuidList") List<String> uuidList);
}