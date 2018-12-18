 package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.dao.DeptDAO;
import com.test4th.base.to.DepartmentBean;



public class BasicDeptAppServiceImpl implements BasicDeptAppService {

    private DeptDAO deptDAO;
    public void setDeptDAO(DeptDAO deptDAO) {
        this.deptDAO = deptDAO;
    }

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
