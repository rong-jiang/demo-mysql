package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.ProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "获取钉钉流程信息接口")
@RequestMapping("/xboot/nailed/process")
@RestController
@Transactional
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping(value = "/addProcess",method = RequestMethod.POST)
    @ApiOperation(value = "钉钉流程信息")
    public int addProcess(){
        return processService.insertSelective();
    }
}
