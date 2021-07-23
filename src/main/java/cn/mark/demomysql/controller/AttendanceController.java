package cn.mark.demomysql.controller;

import cn.mark.demomysql.service.AttendanceService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    /***
     * 将钉钉的打卡记录-->t_attendance打卡记录表中
     * @return
     */
    @RequestMapping("/addAttendance")
    public int addAttendance() {
        return attendanceService.insertSelective();
    }
}
