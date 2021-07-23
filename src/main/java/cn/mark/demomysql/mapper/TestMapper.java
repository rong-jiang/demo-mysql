package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.Test;

public interface TestMapper {
    int deleteByPrimaryKey(Integer testId);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer testId);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}