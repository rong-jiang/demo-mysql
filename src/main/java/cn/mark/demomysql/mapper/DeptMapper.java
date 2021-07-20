package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
    int insert(Dept record);

    int insertSelective(Dept record);
}