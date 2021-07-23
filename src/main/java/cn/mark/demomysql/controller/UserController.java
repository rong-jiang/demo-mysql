package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.UserService;
import com.taobao.api.internal.mapping.ApiField;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ResponseBody
    /*
     * List 里的对象是Map对象，而Map对象是
     * 由一个String类型的键和Object类型的值组成
     * */
    public List<Map<String,Object>> getUsers(){
        String sql="select * from t_book";//SQL查询语句
        List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
        return list;
    }

    /***
     * 将钉钉上部门信息-->t_user用户表..
     * @return
     */
    @RequestMapping("/addUser")
    public int addUser(){
        return userService.insertSelective();
    }
}
