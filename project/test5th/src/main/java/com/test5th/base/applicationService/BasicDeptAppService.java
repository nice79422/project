package com.test5th.base.applicationService;

import java.util.List;

import com.test5th.base.to.DepartmentBean;


public interface BasicDeptAppService {
    public List<DepartmentBean> findDeptList();
    public void batchDept(List<DepartmentBean> deptList);
}
