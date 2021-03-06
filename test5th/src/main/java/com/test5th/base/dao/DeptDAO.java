package com.test5th.base.dao;

import java.util.List;

import com.test5th.base.to.DepartmentBean;

public interface DeptDAO {
    public List<DepartmentBean> selectDeptList();

    public void insertDept(DepartmentBean deptBean);
    public void updateDept(DepartmentBean deptBean);
    public void deleteDept(DepartmentBean deptBean);
}
