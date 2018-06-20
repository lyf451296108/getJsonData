package com.example.demo.mapper;

import com.example.demo.pojo.Logdata;
import com.example.demo.pojo.LogdataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogdataMapper {
    int countByExample(LogdataExample example);

    int deleteByExample(LogdataExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Logdata record);

    int insertSelective(Logdata record);

    List<Logdata> selectByExampleWithBLOBs(LogdataExample example);

    List<Logdata> selectByExample(LogdataExample example);

    Logdata selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Logdata record, @Param("example") LogdataExample example);

    int updateByExampleWithBLOBs(@Param("record") Logdata record, @Param("example") LogdataExample example);

    int updateByExample(@Param("record") Logdata record, @Param("example") LogdataExample example);

    int updateByPrimaryKeySelective(Logdata record);

    int updateByPrimaryKeyWithBLOBs(Logdata record);

    int updateByPrimaryKey(Logdata record);
    
    int insertList(List<Logdata> logDataList);
}