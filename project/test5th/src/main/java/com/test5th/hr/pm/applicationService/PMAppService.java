package com.test5th.hr.pm.applicationService;

import java.util.List;

import com.test5th.hr.pm.to.EmployeeInfoBean;
import com.test5th.hr.pm.to.ReportBean;

public interface PMAppService {
	
	public EmployeeInfoBean findEmployeeInfoAll();

	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean);
	
    public List<ReportBean> findEmpInfoReport(String empCode);

}
