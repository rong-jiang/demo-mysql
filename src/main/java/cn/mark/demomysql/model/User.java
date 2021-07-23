package cn.mark.demomysql.model;

import lombok.Data;

import java.util.Date;

@Data
/**
 * 用户表
 */
public class User {
    private Long id;

    private String userId;

    private String unionId;

    private String name;

    private String avatar;

    private String stateCode;

    private String mobile;

    private String hideMobile;

    private String telephone;

    private String jobNumber;

    private String title;

    private String email;

    private String orgEmail;

    private String workPlace;

    private String remark;

    private String deptIdList;

    private String extension;

    private String admin;

    private String boss;

    private String leader;

    private Date hiredDate;

    private String exclusiveAccount;

    private String loginId;

    private String exclusiveAccountType;

    private Date updateTime;

    public User(Long id, String userId, String unionId, String name, String avatar, String stateCode, String mobile, String hideMobile, String telephone, String jobNumber, String title, String email, String orgEmail, String workPlace, String remark, String deptIdList, String extension, String admin, String boss, String leader, Date hiredDate, String exclusiveAccount, String loginId, String exclusiveAccountType, Date updateTime) {
        this.id = id;
        this.userId = userId;
        this.unionId = unionId;
        this.name = name;
        this.avatar = avatar;
        this.stateCode = stateCode;
        this.mobile = mobile;
        this.hideMobile = hideMobile;
        this.telephone = telephone;
        this.jobNumber = jobNumber;
        this.title = title;
        this.email = email;
        this.orgEmail = orgEmail;
        this.workPlace = workPlace;
        this.remark = remark;
        this.deptIdList = deptIdList;
        this.extension = extension;
        this.admin = admin;
        this.boss = boss;
        this.leader = leader;
        this.hiredDate = hiredDate;
        this.exclusiveAccount = exclusiveAccount;
        this.loginId = loginId;
        this.exclusiveAccountType = exclusiveAccountType;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    public String getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(String deptIdList) {
        this.deptIdList = deptIdList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHideMobile() {
        return hideMobile;
    }

    public void setHideMobile(String hideMobile) {
        this.hideMobile = hideMobile;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public Date getHiredDate() {
        return hiredDate;
    }

    public void setHiredDate(Date hiredDate) {
        this.hiredDate = hiredDate;
    }

    public String getExclusiveAccount() {
        return exclusiveAccount;
    }

    public void setExclusiveAccount(String exclusiveAccount) {
        this.exclusiveAccount = exclusiveAccount;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getExclusiveAccountType() {
        return exclusiveAccountType;
    }

    public void setExclusiveAccountType(String exclusiveAccountType) {
        this.exclusiveAccountType = exclusiveAccountType;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}