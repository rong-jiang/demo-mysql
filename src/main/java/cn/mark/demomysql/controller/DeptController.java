package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.DeptService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 将钉钉上部门信息-->t_dept部门表
     * @return
     */
    @RequestMapping("/addDept")
    public Boolean addDept(){
        return deptService.insertSelective();
    }
}
