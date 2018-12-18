package com.test4th.base.dao;

import java.util.List;

import com.test4th.base.to.EmployeeBean;
import com.test4th.base.to.EmployeeHireDateBean;
import com.test4th.common.dao.IBatisDAO;

public class EmployeeDAOImpl extends IBatisDAO implements EmployeeDAO {

	/*로그인시 사원정보 얻기*/
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeBean> selectEmployeeList() {

		return getSqlMapClientTemplate().queryForList("employee.selectEmployeeList");
	}
	
	/* 사원을 등록하는 메서드 */
	@SuppressWarnings("deprecation")
	@Override
	public void insertEmployee(EmployeeBean employeeBean) {
		System.out.println(employeeBean.getEmpCode()+"등록하려는 사원 정보 뜨는가??");
		getSqlMapClientTemplate().insert("employee.insertEmployee",employeeBean);
	}


	/*로그인시 사원정보 얻기*/
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeBean> selectFilterEmployeeList() {

		return getSqlMapClientTemplate().queryForList("employee.selectFilterEmployeeList");
	}

	/*사원정보관리*/
	@SuppressWarnings("deprecation")
	@Override
	public void updateEmployee(EmployeeBean employeeBean) {
		getSqlMapClientTemplate().update("employee.updateEmployee",employeeBean);
	}

	/* 모든사원의 사원정보, 재직정보를 가지고 오는 메서드 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmployeeHireDateBean> selectEmployeeHireDateList() {
		return getSqlMapClientTemplate().queryForList("employee.selectEmployeeHireDateList");
	}



}
