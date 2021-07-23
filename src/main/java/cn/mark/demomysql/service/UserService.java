package cn.mark.demomysql.service;

import cn.mark.demomysql.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {
    int insert(User record);

    int insertSelective();

    List<User> queryAllUser();

    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     */
    User queryByUserId(@Param("UserId") String userId);

    /**
     * 根据用户id修改数据
     * @param user
     * @return
     */
    int updateUserId(@Param("user") User user);
}