package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.DepartmentBean;
import com.test4th.common.dao.IBatisDAO;

public class DeptDAOImpl extends IBatisDAO implements DeptDAO {

    @Override
	@SuppressWarnings({ "deprecation", "unchecked" })
    public List<DepartmentBean> selectDeptList() {
        return getSqlMapClientTemplate().queryForList("Dept.selectDeptList");
        
    }

	@SuppressWarnings("deprecation")
	@Override
	public void insertDept(DepartmentBean deptBean) {
		getSqlMapClientTemplate().insert("Dept.insertDept",deptBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateDept(DepartmentBean deptBean) {
		getSqlMapClientTemplate().update("Dept.updateDept",deptBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDept(DepartmentBean deptBean) {
		getSqlMapClientTemplate().delete("Dept.deleteDept",deptBean);
	}
}
