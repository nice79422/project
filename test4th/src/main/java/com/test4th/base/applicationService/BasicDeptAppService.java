package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.to.DepartmentBean;


public interface BasicDeptAppService {
    public List<DepartmentBean> findDeptList(String businessPlaceCode);
    public void batchDept(List<DepartmentBean> deptList);
}
