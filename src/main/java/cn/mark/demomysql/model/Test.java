package cn.mark.demomysql.model;

import java.util.Date;

public class Test {
    private Integer testId;

    private String testName;

    private String testTetlie;

    private Date testUpdate;

    private Integer status;

    public Test(Integer testId, String testName, String testTetlie, Date testUpdate, Integer status) {
        this.testId = testId;
        this.testName = testName;
        this.testTetlie = testTetlie;
        this.testUpdate = testUpdate;
        this.status = status;
    }

    public Test() {
        super();
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName == null ? null : testName.trim();
    }

    public String getTestTetlie() {
        return testTetlie;
    }

    public void setTestTetlie(String testTetlie) {
        this.testTetlie = testTetlie == null ? null : testTetlie.trim();
    }

    public Date getTestUpdate() {
        return testUpdate;
    }

    public void setTestUpdate(Date testUpdate) {
        this.testUpdate = testUpdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}