package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.Process;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProcessMapper {
    int insert(Process record);

    int insertSelective(Process record);
}