package cn.mark.demomysql.model;

import lombok.Data;

import java.util.Date;

@Data
public class Dept {
    private Integer id;

    private Long deptId;

    private String name;

    private Long parentId;

    private Date updateTime;

    public Dept(Integer id, Long deptId, String name, Long parentId, Date updateTime) {
        this.id = id;
        this.deptId = deptId;
        this.name = name;
        this.parentId = parentId;
        this.updateTime = updateTime;
    }

    public Dept() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}