package com.test5th.base.applicationService;

import java.util.List;

import com.test5th.base.to.EmployeeBean;
import com.test5th.base.to.EmployeeHireDateBean;


public interface BasicEmployeeAppService {
	
	public List<EmployeeBean> findEmployeeList();
   
	public void batchEmployee(EmployeeBean employeeBean);
	
	public List<EmployeeBean> findFilterEmployeeList();

	public void batcEditEmployee(List<EmployeeBean> employeeList);

    public List<EmployeeHireDateBean> findEmployeeHireDateList();
}
