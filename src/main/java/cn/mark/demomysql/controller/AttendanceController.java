package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.AttendanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(description = "获取钉钉打卡记录接口")
@RequestMapping("/xboot/nailed/attendance")
@RestController
@Transactional
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    /***
     * 将钉钉的打卡记录-->t_attendance打卡记录表中
     * @return
     */
    @RequestMapping(value = "/addAttendance", method = RequestMethod.POST)
    @ApiOperation(value = "将钉钉的打卡记录")
    public int addAttendance() {
        return attendanceService.insertSelective();
    }
}
