package cn.mark.demomysql.model;

import lombok.Data;

@Data
public class ProcessWithBLOBs extends Process{
    private String formComponentValues;

    private String operationRecords;

}
