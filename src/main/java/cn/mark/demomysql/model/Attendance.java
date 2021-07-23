package cn.mark.demomysql.model;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 打卡记录表
 */
public class Attendance {
    private Long id;

    private Long attendanceId;

    private String sourceType;

    private Date baseCheckTime;

    private Date userCheckTime;

    private String procInstId;

    private Long approveId;

    private String locationResult;

    private String timeResult;

    private String checkType;

    private String userId;

    private Date workDate;

    private Long recordId;

    private Long planId;

    private Long groupId;

    private Date updateTime;

    public Attendance(Long id, Long attendanceId, String sourceType, Date baseCheckTime, Date userCheckTime, String procInstId, Long approveId, String locationResult, String timeResult, String checkType, String userId, Date workDate, Long recordId, Long planId, Long groupId, Date updateTime) {
        this.id = id;
        this.attendanceId = attendanceId;
        this.sourceType = sourceType;
        this.baseCheckTime = baseCheckTime;
        this.userCheckTime = userCheckTime;
        this.procInstId = procInstId;
        this.approveId = approveId;
        this.locationResult = locationResult;
        this.timeResult = timeResult;
        this.checkType = checkType;
        this.userId = userId;
        this.workDate = workDate;
        this.recordId = recordId;
        this.planId = planId;
        this.groupId = groupId;
        this.updateTime = updateTime;
    }

    public Attendance() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Date getBaseCheckTime() {
        return baseCheckTime;
    }

    public void setBaseCheckTime(Date baseCheckTime) {
        this.baseCheckTime = baseCheckTime;
    }

    public Date getUserCheckTime() {
        return userCheckTime;
    }

    public void setUserCheckTime(Date userCheckTime) {
        this.userCheckTime = userCheckTime;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public Long getApproveId() {
        return approveId;
    }

    public void setApproveId(Long approveId) {
        this.approveId = approveId;
    }

    public String getLocationResult() {
        return locationResult;
    }

    public void setLocationResult(String locationResult) {
        this.locationResult = locationResult;
    }

    public String getTimeResult() {
        return timeResult;
    }

    public void setTimeResult(String timeResult) {
        this.timeResult = timeResult;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}