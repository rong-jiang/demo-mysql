package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.controller.NailedController;
import cn.mark.demomysql.mapper.AttendanceMapper;
import cn.mark.demomysql.model.Attendance;
import cn.mark.demomysql.service.AttendanceService;
import cn.mark.demomysql.ulit.DateTool;
import com.dingtalk.api.response.OapiAttendanceListResponse;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Override
    public int insert(Attendance record) {
        return 0;
    }

    /**
     * 把钉钉的打卡记录-->t_attendance打卡记录表中
     *
     * @return
     */
    @Override
    public int insertSelective() {
        Date now = new Date();
        List<OapiAttendanceListResponse.Recordresult> attendanceList = new ArrayList<>();
        //获取前几天时间
        String workDate = DateTool.getDateBefore(new Date(), 7);
        //获取当前时间
        String nowDate = DateTool.formatDate(new Date());
        String startWorkDate = workDate + " 00:00:00";
        String endWorkDate = nowDate + " 23:59:59";
        try {
            List<OapiV2DepartmentListsubResponse.DeptBaseResponse> department = NailedController.getDepartment();
            if (department != null && department.size() > 0) {
                List<String> userIdList = new ArrayList<>();
                department.forEach(dept -> {
                    List<String> departmentUser = NailedController.getDepartmentUserId(dept.getDeptId());

                    if (departmentUser != null && departmentUser.size() > 0) {
                        userIdList.addAll(departmentUser);
                    }
                });
                if (userIdList != null && userIdList.size() > 0) {
                    //按指定长度进行切分
                    List<List<String>> userIds = Lists.partition(userIdList, 50);
                    userIds.forEach(userId -> {
                        int count = 1;
                        long offset = 0L;
                        long limit = 50L;
                        List<OapiAttendanceListResponse.Recordresult> attendanceListTmp = NailedController.getAttendanceList(startWorkDate, endWorkDate, userId, offset, limit);
                        if (attendanceListTmp != null && attendanceListTmp.size() > 0) {
                            attendanceList.addAll(attendanceListTmp);
                            while (attendanceListTmp.size() <= 50) {
                                count++;
                                offset = (count - 1) * limit;
                                attendanceListTmp = NailedController.getAttendanceList(startWorkDate, endWorkDate, userId, offset, limit);
                                if (attendanceListTmp == null || attendanceListTmp.size() == 0) {
                                    break;
                                }
                                attendanceList.addAll(attendanceListTmp);
                            }
                        }
                    });
                }
                if (attendanceList !=null && attendanceList.size()>0){
                    attendanceList.forEach(attendance-> {
                        Attendance attendance1 = new Attendance();
                        attendance1.setAttendanceId(attendance.getId());
                        attendance1.setSourceType(attendance.getSourceType());
                        attendance1.setBaseCheckTime(attendance.getBaseCheckTime());
                        attendance1.setUserCheckTime(attendance.getUserCheckTime());
                        attendance1.setProcInstId(attendance.getProcInstId());
                        attendance1.setApproveId(attendance.getApproveId());
                        attendance1.setLocationResult(attendance.getLocationResult());
                        attendance1.setTimeResult(attendance.getTimeResult());
                        attendance1.setCheckType(attendance.getCheckType());
                        attendance1.setUserId(attendance.getUserId());
                        attendance1.setWorkDate(attendance.getWorkDate());
                        attendance1.setRecordId(attendance.getRecordId());
                        attendance1.setPlanId(attendance.getPlanId());
                        attendance1.setGroupId(attendance.getGroupId());
                        attendance1.setUpdateTime(now);
                        attendanceMapper.insertSelective(attendance1);
                    });
                    return 1;
                }
            }
        } catch (Exception e) {
            log.error("添加t_user部门表失败:{}",e);
        }
        return 0;
    }
}
