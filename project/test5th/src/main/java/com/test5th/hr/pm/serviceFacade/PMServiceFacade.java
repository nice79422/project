package com.test5th.hr.pm.serviceFacade;

import java.util.List;

import com.test5th.hr.pm.to.EmployeeInfoBean;
import com.test5th.hr.pm.to.ReportBean;

public interface PMServiceFacade {
	public EmployeeInfoBean findEmployeeInfoAll();
	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean);
	public List<ReportBean> findEmpInfoReport(String empCode);
	
}

