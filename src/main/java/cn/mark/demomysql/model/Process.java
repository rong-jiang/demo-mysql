package cn.mark.demomysql.model;

import lombok.Data;

import java.util.Date;

@Data
public class Process {
    private Long id;

    private String processInstanceId;

    private String processCode;

    private String title;

    private Date createTime;

    private Date finishTime;

    private String originatorUserid;

    private Long originatorDeptId;

    private String originatorDeptName;

    private String status;

    private String result;

    private String businessId;

    private String bizAction;

    private String mainProcessInstanceId;

    private Date updateTime;

    public Process(Long id, String processInstanceId, String processCode, String title, Date createTime, Date finishTime, String originatorUserid, Long originatorDeptId, String originatorDeptName, String status, String result, String businessId, String bizAction, String mainProcessInstanceId, Date updateTime) {
        this.id = id;
        this.processInstanceId = processInstanceId;
        this.processCode = processCode;
        this.title = title;
        this.createTime = createTime;
        this.finishTime = finishTime;
        this.originatorUserid = originatorUserid;
        this.originatorDeptId = originatorDeptId;
        this.originatorDeptName = originatorDeptName;
        this.status = status;
        this.result = result;
        this.businessId = businessId;
        this.bizAction = bizAction;
        this.mainProcessInstanceId = mainProcessInstanceId;
        this.updateTime = updateTime;
    }

    public Process() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessCode() {
        return processCode;
    }

    public void setProcessCode(String processCode) {
        this.processCode = processCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getOriginatorUserid() {
        return originatorUserid;
    }

    public void setOriginatorUserid(String originatorUserid) {
        this.originatorUserid = originatorUserid;
    }

    public Long getOriginatorDeptId() {
        return originatorDeptId;
    }

    public void setOriginatorDeptId(Long originatorDeptId) {
        this.originatorDeptId = originatorDeptId;
    }

    public String getOriginatorDeptName() {
        return originatorDeptName;
    }

    public void setOriginatorDeptName(String originatorDeptName) {
        this.originatorDeptName = originatorDeptName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBizAction() {
        return bizAction;
    }

    public void setBizAction(String bizAction) {
        this.bizAction = bizAction;
    }

    public String getMainProcessInstanceId() {
        return mainProcessInstanceId;
    }

    public void setMainProcessInstanceId(String mainProcessInstanceId) {
        this.mainProcessInstanceId = mainProcessInstanceId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}