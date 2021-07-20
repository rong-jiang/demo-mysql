package cn.mark.demomysql.service;

import cn.mark.demomysql.model.Dept;

public interface DeptService {
    int insert(Dept record);

    Boolean insertSelective();
}