package com.example.demo.pojo;

import java.util.Date;

public class CallInformation {
    private Integer id;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType == null ? null : callType.trim();
    }

    public String getCallerNum() {
        return callerNum;
    }

    public void setCallerNum(String callerNum) {
        this.callerNum = callerNum == null ? null : callerNum.trim();
    }

    public String getCalledNum() {
        return calledNum;
    }

    public void setCalledNum(String calledNum) {
        this.calledNum = calledNum == null ? null : calledNum.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(String timeLength) {
        this.timeLength = timeLength == null ? null : timeLength.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getRecordURL() {
        return recordURL;
    }

    public void setRecordURL(String recordURL) {
        this.recordURL = recordURL == null ? null : recordURL.trim();
    }

    public String getOwnerAcc() {
        return ownerAcc;
    }

    public void setOwnerAcc(String ownerAcc) {
        this.ownerAcc = ownerAcc == null ? null : ownerAcc.trim();
    }

    public String getCommunicationNO() {
        return communicationNO;
    }

    public void setCommunicationNO(String communicationNO) {
        this.communicationNO = communicationNO == null ? null : communicationNO.trim();
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId == null ? null : followId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}