package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.controller.NailedController;
import cn.mark.demomysql.mapper.UserMapper;
import cn.mark.demomysql.model.User;
import cn.mark.demomysql.service.UserService;
import com.alibaba.fastjson.JSON;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.dingtalk.api.response.OapiV2UserListResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int insert(User record) {
        return 0;
    }

    /***
     * 获取钉钉人员-->t_user表中
     * @param
     * @return
     */
    @Override
    public int insertSelective() {
        Date now = new Date();
        try {
            List<OapiV2UserListResponse.ListUserResponse> userList = new ArrayList();
            List<OapiV2DepartmentListsubResponse.DeptBaseResponse> department = NailedController.getDepartment();
            if (department != null && department.size() > 0) {
                department.forEach(dept -> {
                    long cursor = 0L;
                    long size = 10L;
                    OapiV2UserListResponse.PageResult departmentUser = NailedController.getDepartmentUser(dept.getDeptId(), cursor, size);
                    //分页查询的游标(cursor)，最开始传0，后续传返回参数中的next_cursor值。
                    //条件循环
                    while (departmentUser != null && departmentUser.getHasMore()) {
                        userList.addAll(departmentUser.getList());
                        cursor = departmentUser.getNextCursor();
                        departmentUser = NailedController.getDepartmentUser(dept.getDeptId(), cursor, size);
                    }
                    if (departmentUser != null && departmentUser.getList() != null && departmentUser.getList().size() > 0) {
                        userList.addAll(departmentUser.getList());
                    }
                });

                if (userList != null && userList.size() > 0) {
                    //不管有没有 先删除
                    userMapper.deleteByIdAll();
                    userList.forEach(user -> {
                        //User user2 = userMapper.queryByUserIds(user.getUserid());
                        User user1 = new User();
                        //将user对象中的数据赋个user1
                        /*BeanUtils.copyProperties(user,user1);*/
                        user1.setUserId(user.getUserid());
                        user1.setUnionId(user.getUnionid());
                        user1.setName(user.getName());
                        user1.setAvatar(user.getAvatar());
                        user1.setStateCode(user.getStateCode());
                        user1.setMobile(user.getMobile());
                        user1.setHideMobile(user.getHideMobile() ? "true" : "false");
                        user1.setTelephone(user.getTelephone());
                        user1.setJobNumber(user.getJobNumber());
                        user1.setTitle(user.getTitle());
                        user1.setEmail(user.getEmail());
                        user1.setOrgEmail(user.getOrgEmail());
                        user1.setWorkPlace(user.getWorkPlace());
                        user1.setRemark(user.getRemark());
                        user1.setDeptIdList(JSON.toJSONString(user.getDeptIdList()));
                        user1.setExtension(user.getExtension());
                        user1.setAdmin(user.getAdmin() ? "true" : "false");
                        user1.setBoss(user.getBoss() ? "true" : "false");
                        user1.setLeader(user.getLeader() ? "true" : "false");
                        if (user.getHiredDate() != null) {
                            user1.setHiredDate(new Date(Long.valueOf(user.getHiredDate())));
                        }
                        user1.setExclusiveAccountType(user.getExclusiveAccountType());
                        user1.setExclusiveAccount(user.getExclusiveAccount() ? "true" : "false");
                        user1.setLoginId(user.getLoginId());
                        user1.setUpdateTime(now);
                        /*if (user2 !=null){
                            userMapper.updateUserId(user1);
                        }else{
                            userMapper.insertSelective(user1);
                        }*/
                        userMapper.insertSelective(user1);
                    });
                    return 1;
                }
            }
        } catch (Exception e) {
            log.error("添加t_user部门表失败:{}", e);
        }
        return 0;
    }

    /**
     * 查询用户所有数据t_user
     * @return
     */
    @Override
    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    /**
     * 根据用户id查询数据
     * @param userId
     * @return
     */
    @Override
    public User queryByUserId(String userId) {
        return userMapper.queryByUserId(userId);
    }

    /**
     * 根据用户id修改数据
     * @param user
     * @return
     */
    @Override
    public int updateUserId(User user) {
        return updateUserId(user);
    }

}
