package cn.mark.demomysql.service;

import cn.mark.demomysql.model.Attendance;
import org.apache.ibatis.annotations.Mapper;

public interface AttendanceService {
    int insert(Attendance record);

    int insertSelective();
}