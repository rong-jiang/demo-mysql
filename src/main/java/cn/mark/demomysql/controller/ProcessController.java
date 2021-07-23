package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @RequestMapping("/addProcess")
    public int addProcess(){
        return processService.insertSelective();
    }
}
