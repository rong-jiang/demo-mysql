package cn.mark.demomysql.mapper;

import cn.mark.demomysql.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    int insert(User record);

    int insertSelective(User record);

    /**
     * 查询用户所有数据
     * @return
     */
    List<User> queryAllUser();

    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     */
    @Select("select * from t_user where user_id=#{userId}")
    User queryByUserIds(@Param("userId") String userId);

    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     */
    User queryByUserId(@Param("userId") String userId);
    /**
     * 根据用户id修改数据
     * @param user
     * @returns
     */
    int updateUserId(User user);

    /**
     * 删除全部user数据
     * @return
     */
    int deleteByIdAll();
}