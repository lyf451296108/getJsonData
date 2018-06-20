package com.example.demo.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CallInformationEntity {
	
	private String callType;
	
	private String callerNum;
	
	private String calledNum;
	
	private String startTime;
	
	private String timeLength;
	
	private String code;
	
	private String recordURL;
	
	private String ownerAcc;
	
	private String communicationNO;
	
	private String followId;
	
	private Date createTime;
	
}
