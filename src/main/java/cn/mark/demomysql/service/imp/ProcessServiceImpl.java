package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.controller.NailedController;
import cn.mark.demomysql.mapper.ProcessMapper;
import cn.mark.demomysql.model.Process;
import cn.mark.demomysql.model.ProcessWithBLOBs;
import cn.mark.demomysql.service.ProcessService;
import cn.mark.demomysql.ulit.DateTool;
import com.alibaba.fastjson.JSON;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.dingtalk.api.response.OapiProcessinstanceListidsResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessMapper processMapper;

    @Override
    public int insert(Process record) {
        return 0;
    }

    /**
     * 将钉钉上的获取工作流程数据-->t_process表中
     *
     * @return
     */
    @Override
    public int insertSelective() {
        String type = "";
        //请假
        String processCode = NailedController.LEAVE_PROCESS_CODE;
        log.info("没有吗============：{}", processCode);
        if (StringUtils.isNotBlank(processCode)) {
            if (NailedController.TRANSFER_PROCESS_CODE.equals(processCode)) {
                type = "transfer";//调岗
            } else if (NailedController.TRAVELWORK_PROCESS_CODE.equals(processCode)) {
                type = "travelwork";//出差
            } else if (NailedController.PUBLIC_PROCESS_CODE.equals(processCode)) {
                type = "public";//外出
            } else if (NailedController.LEAVE_PROCESS_CODE.equals(processCode)) {
                type = "leave";//请假
            }
        }
        try {

            List<ProcessWithBLOBs> processinstanceList = getProcessinstanceListid(processCode);
            log.info("这里的数据=================：{}", processinstanceList);
            if (processinstanceList != null && processinstanceList.size() > 0) {
//                hrDingdingService.insertOrUpdateProcessInstance(type, processinstanceList);
                processinstanceList.forEach(processList -> {
                    processMapper.insertSelective(processList);
                });
                return 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * @param processCode
     * @return
     */
    public List<ProcessWithBLOBs> getProcessinstanceListid(String processCode) {
        //用户id，多个以逗号分开
        String userIdList = "";
        Date now = new Date();
        //仅仅获取30天以为的工作流程
        String workDate = DateTool.getDateBefore(now, 30);
        String startDate = workDate + " 00:00:00";
        //long startTime = Long.valueOf(DateUtils.stringToTime(startDate, EnumDateStyle.YYYY_MM_DD_HH_MM_SS));
        long startTime = DateTool.StringToLong(startDate);
        List<ProcessWithBLOBs> processinstanceList = new ArrayList<>();
        List<String> processinstanceListids = new ArrayList<>();
        long cursor = 0L;
        long size = 10L;
        OapiProcessinstanceListidsResponse.PageResult processinstanceListidsResponse = NailedController.getProcessinstanceListid(processCode, startTime, null, cursor, size, userIdList);
        while (processinstanceListidsResponse != null && processinstanceListidsResponse.getNextCursor() != null) {
            processinstanceListids.addAll(processinstanceListidsResponse.getList());
            cursor = processinstanceListidsResponse.getNextCursor();
            processinstanceListidsResponse = NailedController.getProcessinstanceListid(processCode, startTime, null, cursor, size, userIdList);
        }
        if (processinstanceListidsResponse != null && processinstanceListidsResponse.getList() != null && processinstanceListidsResponse.getList().size() > 0) {
            processinstanceListids.addAll(processinstanceListidsResponse.getList());
        }
        if (processinstanceListids != null && processinstanceListids.size() > 0) {
            for (String processInstanceId : processinstanceListids) {
                OapiProcessinstanceGetResponse.ProcessInstanceTopVo processInstanceTopVo = NailedController.getProcessinstanceInfo(processInstanceId);
                if (processInstanceTopVo != null) {
                    ProcessWithBLOBs hrDingdingProcess = new ProcessWithBLOBs();
                    hrDingdingProcess.setProcessInstanceId(processInstanceId);
                    hrDingdingProcess.setProcessCode(processCode);
                    hrDingdingProcess.setTitle(processInstanceTopVo.getTitle());
                    hrDingdingProcess.setCreateTime(processInstanceTopVo.getCreateTime());
                    hrDingdingProcess.setFinishTime(processInstanceTopVo.getFinishTime());
                    if (StringUtils.isNotBlank(processInstanceTopVo.getOriginatorDeptId())) {
                        hrDingdingProcess.setOriginatorDeptId(Long.valueOf(processInstanceTopVo.getOriginatorDeptId()));
                    }
                    hrDingdingProcess.setOriginatorDeptName(processInstanceTopVo.getOriginatorDeptName());
                    hrDingdingProcess.setOriginatorUserid(processInstanceTopVo.getOriginatorUserid());
                    hrDingdingProcess.setStatus(processInstanceTopVo.getStatus());
                    hrDingdingProcess.setResult(processInstanceTopVo.getResult());
                    hrDingdingProcess.setBusinessId(processInstanceTopVo.getBusinessId());
                    hrDingdingProcess.setBizAction(processInstanceTopVo.getBizAction());
                    hrDingdingProcess.setMainProcessInstanceId(processInstanceTopVo.getMainProcessInstanceId());
                    hrDingdingProcess.setFormComponentValues(JSON.toJSONString(processInstanceTopVo.getFormComponentValues()));
                    hrDingdingProcess.setOperationRecords(JSON.toJSONString(processInstanceTopVo.getOperationRecords()));
                    hrDingdingProcess.setUpdateTime(now);

                    processinstanceList.add(hrDingdingProcess);
                }
            }
        }
        return processinstanceList;
    }
}
