package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class logBeanEntity {
    //批号id
	private String batchId;
	
	//备注说明
	private String content;
	
	//资源JSON数据
	private String data;
	
	//模块id
	private String moduleId;
	
	//模块名称
	private String moduleName;
	
	//操作类型id
	private String operateId;
	
	//操作类型名称
	private String operateName;
	
	//资源操作数量
	private Integer operateNum;
	
	//单位id
	private String orgId;
	
	//资源持有人账号
	private String ownerAcc;
	
	//资源操作类型id
	private String resOperateId;
	
	//资源操作类型名称
	private String resOperateName;
	
	//操作人账号
	private String userAcc;
	
	//操作人名称
	private String userName;
	
	//资源id字段
	private String resouceId;
	
	//操作描述
	private String operateDes;
		
		
}
