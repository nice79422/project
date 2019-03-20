package com.test4th.hr.pm.applicationService;

import com.test4th.hr.pm.to.EmployeeInfoBean;

public interface PMAppService {
	
	public EmployeeInfoBean findEmployeeInfoAll();

	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean);

}
