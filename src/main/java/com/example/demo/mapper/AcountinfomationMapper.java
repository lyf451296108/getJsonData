package com.example.demo.mapper;

import com.example.demo.pojo.Acountinfomation;
import com.example.demo.pojo.AcountinfomationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AcountinfomationMapper {
    int countByExample(AcountinfomationExample example);

    int deleteByExample(AcountinfomationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Acountinfomation record);

    int insertSelective(Acountinfomation record);

    List<Acountinfomation> selectByExample(AcountinfomationExample example);

    Acountinfomation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Acountinfomation record, @Param("example") AcountinfomationExample example);

    int updateByExample(@Param("record") Acountinfomation record, @Param("example") AcountinfomationExample example);

    int updateByPrimaryKeySelective(Acountinfomation record);

    int updateByPrimaryKey(Acountinfomation record);
    
    int insertAccountInforList(List<Acountinfomation> recordList);
    
    int insertOrUpdateAccountInforList(List<Acountinfomation> recordList);
}