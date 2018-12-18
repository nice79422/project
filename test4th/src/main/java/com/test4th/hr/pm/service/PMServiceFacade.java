package com.test4th.hr.pm.service;

import com.test4th.hr.pm.to.EmployeeInfoBean;

public interface PMServiceFacade {
	public EmployeeInfoBean findEmployeeInfoAll();
	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean);
}
