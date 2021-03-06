package cn.mark.demomysql.controller;

import cn.mark.demomysql.mapper.DeptMapper;
import cn.mark.demomysql.model.Dept;
import cn.mark.demomysql.ulit.LocalCacheClient;
import com.alibaba.druid.util.StringUtils;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.*;
import com.dingtalk.api.response.*;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Api(description = "获钉钉调式接口")
@RequestMapping("/xboot/nailed/nailed")
@RestController
@Transactional
/**
 * ulit类 写这位测试接口
 *
 */
public class NailedController   {

    private final static String SYSTEM_ERROR ="SYSTEM_ERROR";

    private final static String APPKEY ="dingnyj1pmwaslys5pdw";

    private final static String APPSECRET="pat8utGgu_HAA35JMFJv-d5GzC7roRkKV2SytUG1VyUD57G3NmVRgau2vVozELMr";

    private final static Long AGENTID = 0L;

    //请假
    public final static String LEAVE_PROCESS_CODE = "PROC-A762FCC5-DB30-44A2-BABF-3AFBF432A58E";
    //外出
    public final static String PUBLIC_PROCESS_CODE = "PROC-060C3355-6256-417F-AD4B-C10DCC73349D";
    //出差
    public final static String TRAVELWORK_PROCESS_CODE = "PROC-5E074F96-7C9F-4B12-A7C5-D83C6CAA5C8B";
    //调岗
    public final static String TRANSFER_PROCESS_CODE = "PROC-C3EA16BA-C395-44FE-B35F-A412C0167E19";


    @Autowired
    private DeptMapper deptMapper;

    //获取token
    @RequestMapping(value = "/getToken",method = RequestMethod.GET)
    @ApiOperation(value = "获取钉钉token")
    public static String getToken (){
        Object object = LocalCacheClient.get("access_token");
        if(object != null){
            return object.toString();
        }
        DingTalkClient client = new
                DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(NailedController.APPKEY);
        request.setAppsecret(NailedController.APPSECRET);
        request.setHttpMethod("GET");
        try {
            OapiGettokenResponse response = client.execute(request);
            LocalCacheClient.set("access_token", response.getAccessToken(),7200*1000);
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.getAccessToken();
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取部门列表
    @RequestMapping(value = "/getDepartment",method = RequestMethod.GET)
    @ApiOperation(value = "获取钉钉获取部门列表")
    public static List<OapiV2DepartmentListsubResponse.DeptBaseResponse> getDepartment(){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsub");
        OapiV2DepartmentListsubRequest request = new OapiV2DepartmentListsubRequest();
        //获取根部门下所有部门列表  根部门的部门id为1
//        request.setDeptId(1L);
        request.setHttpMethod("GET");
        try {
            OapiV2DepartmentListsubResponse response = client.execute(request, NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess() ? response.getResult():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取部门下的所有用户列表(不推荐)
    @RequestMapping(value = "/getDepartmentUserId",method = RequestMethod.POST)
    @ApiOperation(value = "获取部门下的所有用户列表(不推荐)")
    public static List<String> getDepartmentUserId(Long departmentId){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/user/listid");
        OapiUserListidRequest req = new OapiUserListidRequest();
        req.setDeptId(departmentId);
//        req.setHttpMethod("GET");
        try {
            OapiUserListidResponse response = client.execute(req, NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess()?response.getResult().getUseridList():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取部门下的所有用户列表
    @RequestMapping(value = "/getDepartmentUser",method = RequestMethod.GET)
    @ApiOperation(value = "获取部门下的所有用户列表")
    public static OapiV2UserListResponse.PageResult getDepartmentUser(Long departmentId, long cursor, long size){
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/user/list");
        OapiV2UserListRequest request = new OapiV2UserListRequest();
        request.setDeptId(departmentId);
        request.setCursor(cursor);
        request.setSize(size);
        request.setOrderField("modify_desc");
        request.setContainAccessLimit(false);
        request.setLanguage("zh_CN");
        request.setHttpMethod("GET");

        try {
            OapiV2UserListResponse  response = client.execute(request, NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess()?response.getResult():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取钉钉考勤记录
    @RequestMapping(value = "/getAttendanceList",method = RequestMethod.POST)
    @ApiOperation(value = "获取钉钉考勤记录")
    public static List<OapiAttendanceListResponse.Recordresult> getAttendanceList(String startWorkDate, String endWorkDate, List<String> userIdList, long offset, long limit) {
        // 通过调用接口获取考勤打卡结果
        DingTalkClient clientDingTalkClient = new DefaultDingTalkClient("https://oapi.dingtalk.com/attendance/list");
        OapiAttendanceListRequest requestAttendanceListRequest = new OapiAttendanceListRequest();
        // 查询考勤打卡记录的起始工作日
        requestAttendanceListRequest.setWorkDateFrom(startWorkDate);
        // 查询考勤打卡记录的结束工作日
        requestAttendanceListRequest.setWorkDateTo(endWorkDate);
        // 员工在企业内的userid列表，最多不能超过50个。
        requestAttendanceListRequest.setUserIdList(userIdList);
        // 表示获取考勤数据的起始点
        requestAttendanceListRequest.setOffset(offset);
        // 表示获取考勤数据的条数，最大不能超过50条。
        requestAttendanceListRequest.setLimit(limit);
        OapiAttendanceListResponse response = null;
        try {
            response = clientDingTalkClient.execute(requestAttendanceListRequest,NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess()?response.getRecordresult():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取审批实例ID列表
    @RequestMapping(value = "/getProcessinstanceListid",method = RequestMethod.POST)
    @ApiOperation(value = "获取审批实例ID列表")
    public static OapiProcessinstanceListidsResponse.PageResult getProcessinstanceListid(String processCode, Long startTime, Long endTime, long cursor, long size,String userIdList) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/listids");
            OapiProcessinstanceListidsRequest request = new OapiProcessinstanceListidsRequest();
            request.setProcessCode(processCode);
            request.setStartTime(startTime);
            if(endTime != null) {
                request.setEndTime(endTime);
            }
            request.setSize(size);
            request.setCursor(cursor);
            if (userIdList != null || !"".equals(userIdList)){
                request.setUseridList(userIdList);
            }
            OapiProcessinstanceListidsResponse response = client.execute(request, NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess()?response.getResult():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }

    //获取审批实例详情
    @RequestMapping(value = "/getProcessinstanceInfo",method = RequestMethod.POST)
    @ApiOperation(value = "获取审批实例详情")
    public static OapiProcessinstanceGetResponse.ProcessInstanceTopVo getProcessinstanceInfo(String processInstanceId) {
        try {
            DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
            OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
            request.setProcessInstanceId(processInstanceId);
            OapiProcessinstanceGetResponse response = client.execute(request, NailedController.getToken());
            if(!response.isSuccess()) {
                log.error("调用钉钉接口失败："+response.getMessage());
            }
            return response.isSuccess()?response.getProcessInstance():null;
        } catch (ApiException e) {
            log.error(NailedController.SYSTEM_ERROR, e);
        }
        return null;
    }


    /***
     * 将钉钉上部门信息-->t_dept部门表
     * @return
     */
   /* @RequestMapping("/addDept")
    public Boolean addDept(){
        Date date=new Date();
        List<OapiV2DepartmentListsubResponse.DeptBaseResponse> department = NailedController.getDepartment();
        if (department.size()>0 && department !=null){
            department.forEach(dept->{
                Dept dept1 =new Dept();
                dept1.setDeptId(dept.getDeptId());
                dept1.setName(dept.getName());
                dept1.setParentId(dept.getParentId());
                dept1.setUpdateTime(date);
                deptMapper.insert(dept1);
            });
            return true;
        }else
            return false;
    }*/

    public static void main(String[] args) {
//        List<OapiV2UserListResponse.ListUserResponse> userList = new ArrayList();
//        OapiV2UserListResponse.PageResult userPageResult = NailedController.getDepartmentUser(90623955L, 1L, 50L);
//        List<String> departmentUserId = NailedController.getDepartmentUserId(90623955L);
//        long cursor = 0L;
//        long size = 50L;
//        while (userPageResult != null && userPageResult.getHasMore()) {
//            userList.addAll(userPageResult.getList());
//            cursor = userPageResult.getNextCursor();
//            userPageResult = NailedController.getDepartmentUser(90623955L, cursor, size);
//        }
//        if(userPageResult != null && userPageResult.getList() != null && userPageResult.getList().size() > 0) {
//            userList.addAll(userPageResult.getList());
//        }
//        for (OapiV2UserListResponse.ListUserResponse listUserResponse : userPageResult.getList()) {
//            System.out.println(listUserResponse.getName());
//        }

        List<OapiV2DepartmentListsubResponse.DeptBaseResponse> departmentList = NailedController.getDepartment();
        if(departmentList != null && departmentList.size() > 0) {
            List<String> userIdList = new ArrayList<>();
            for (OapiV2DepartmentListsubResponse.DeptBaseResponse department : departmentList) {
                List<String> userIdListTmp = NailedController.getDepartmentUserId(department.getDeptId());
                if (userIdListTmp != null && userIdListTmp.size() > 0) {
                    userIdList.addAll(userIdListTmp);
                }
            }

            for (String s : userIdList) {
                System.out.println(s);
            }
            log.info("打印输出数据:{}",userIdList.size());
            //按指定长度进行切分
            List<List<String>> userIds = Lists.partition(userIdList, 50);
            for (List<String> userId : userIds) {
                System.out.println("加工后数据"+userId);
            }
        }

    }


}
