package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.Attendance;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttendanceMapper {
    int insert(Attendance record);

    int insertSelective(Attendance record);
}