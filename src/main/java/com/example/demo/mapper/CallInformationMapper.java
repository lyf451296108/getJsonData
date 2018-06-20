package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.example.demo.pojo.CallInformation;
import com.example.demo.pojo.CallInformationExample;

public interface CallInformationMapper {
    int countByExample(CallInformationExample example);

    int deleteByExample(CallInformationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CallInformation record);

    int insertSelective(CallInformation record);

    List<CallInformation> selectByExample(CallInformationExample example);

    CallInformation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CallInformation record, @Param("example") CallInformationExample example);

    int updateByExample(@Param("record") CallInformation record, @Param("example") CallInformationExample example);

    int updateByPrimaryKeySelective(CallInformation record);

    int updateByPrimaryKey(CallInformation record);
    
    int insertList(List<CallInformation> callInformationList);
}