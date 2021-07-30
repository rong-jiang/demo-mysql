package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.DeptService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "获取钉钉部门信息接口")
@RequestMapping("/xboot/nailed/dept")
@RestController
@Transactional
public class DeptController {

    @Autowired
    private DeptService deptService;

    /**
     * 将钉钉上部门信息-->t_dept部门表
     * @return
     */
    @RequestMapping(value = "/addDept", method = RequestMethod.POST)
    @ApiOperation(value = "将钉钉上部门信息")
    public Boolean addDept(){
        return deptService.insertSelective();
    }
}
