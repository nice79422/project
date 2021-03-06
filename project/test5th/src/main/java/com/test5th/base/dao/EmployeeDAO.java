package com.test5th.base.dao;

import java.util.List;

import com.test5th.base.to.EmployeeBean;
import com.test5th.base.to.EmployeeHireDateBean;


public interface EmployeeDAO {
	
	public EmployeeBean selectEmployee(String empCode);
	
	public List<EmployeeBean> selectEmployeeList();
	
	public void insertEmployee(EmployeeBean employeeBean);
	
	public List<EmployeeBean> selectFilterEmployeeList();
	
	public void updateEmployee(EmployeeBean employeeBean);
	

	public List<EmployeeHireDateBean> selectEmployeeHireDateList();
	
}
