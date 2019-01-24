package com.test5th.hr.pm.serviceFacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test5th.hr.pm.applicationService.PMAppService;
import com.test5th.hr.pm.to.EmployeeInfoBean;
import com.test5th.hr.pm.to.ReportBean;

@Service
public class PMServiceFacadeImpl implements PMServiceFacade{
	/* PMAppService setting */
	@Autowired
	private PMAppService pmAppService;

	/* 사원의 모든상세정보를 가지고 오는 메서드 */
	@Override
	public EmployeeInfoBean findEmployeeInfoAll() {
		return pmAppService.findEmployeeInfoAll();
	}

	//사원관련된 정보를 일괄처리하는 메서드 
	@Override
	public void batchEmployeeInfoAll(EmployeeInfoBean employeeInfoBean) {
		 pmAppService.batchEmployeeInfoAll(employeeInfoBean);
	}

	@Override
	public List<ReportBean> findEmpInfoReport(String empCode) {
		return pmAppService.findEmpInfoReport(empCode);
	}
	
	
}
