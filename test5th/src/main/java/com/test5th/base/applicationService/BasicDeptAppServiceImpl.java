 package com.test5th.base.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.base.dao.DeptDAO;
import com.test5th.base.to.DepartmentBean;


@Component
public class BasicDeptAppServiceImpl implements BasicDeptAppService {
    
	@Autowired
    private DeptDAO deptDAO;
    

    @Override
    public List<DepartmentBean> findDeptList() {
        return deptDAO.selectDeptList();
    }

	@Override
	public void batchDept(List<DepartmentBean> deptList) {
		for(DepartmentBean deptBean:deptList){
			switch(deptBean.getStatus()){ /*mapper에서 bean들의  rowType를 읽어 온듯?? 필요없는경우에 받아만 놓고 사용 안함*/
				case "insert" : deptDAO.insertDept(deptBean); break;
				case "update" : deptDAO.updateDept(deptBean); break;
				case "delete" : deptDAO.deleteDept(deptBean); break;
			}
		}
	}

}
