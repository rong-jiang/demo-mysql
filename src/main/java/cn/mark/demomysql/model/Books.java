package cn.mark.demomysql.model;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data

public class Books implements Serializable{
    private String exceptionDescription;

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }
}