package cn.mark.demomysql.service.imp;

import cn.mark.demomysql.controller.NailedController;
import cn.mark.demomysql.mapper.DeptMapper;
import cn.mark.demomysql.model.Dept;
import cn.mark.demomysql.service.DeptService;
import com.dingtalk.api.response.OapiV2DepartmentListsubResponse;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public int insert(Dept record) {
       return 0;
    }

    /***
     * 将钉钉上部门信息-->t_dept部门表
     * @return
     */
    @Override
    public Boolean insertSelective() {
//        NailedController nailedController=new NailedController();
        Date date=new Date();
        List<OapiV2DepartmentListsubResponse.DeptBaseResponse> department = NailedController.getDepartment();
        if (department.size()>0 && department !=null){
            department.forEach(dept->{
                Dept dept1 =new Dept();
                dept1.setDeptId(dept.getDeptId());
                dept1.setName(dept.getName());
                dept1.setParentId(dept.getParentId());
                dept1.setUpdateTime(date);
                deptMapper.insertSelective(dept1);
            });
            return true;
        }else{
            log.error("添加t_dept部门表失败");
            return false;
        }

    }


}
