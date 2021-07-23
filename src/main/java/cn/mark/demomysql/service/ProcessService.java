package cn.mark.demomysql.service;

import cn.mark.demomysql.model.Process;
import org.apache.ibatis.annotations.Mapper;


public interface ProcessService {
    int insert(Process record);

    int insertSelective();
}