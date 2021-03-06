package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.UserService;
import com.taobao.api.internal.mapping.ApiField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@Api(description = "获取钉钉用户接口")
@RequestMapping("/xboot/nailed/user")
@RestController
@Transactional
public class UserController {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers",method = RequestMethod.GET)
    @ApiOperation(value = "获取用户数据")
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
     * 将钉钉上用户信息-->t_user用户表..
     * @return
     */
    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    @ApiOperation(value = "将钉钉上用户信息")
    public int addUser(){
        return userService.insertSelective();
    }
}
