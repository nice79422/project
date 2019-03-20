package com.test4th.hr.pm.service;

import com.test4th.hr.pm.applicationService.PMAppService;
import com.test4th.hr.pm.to.EmployeeInfoBean;

public class PMServiceFacadeImpl implements PMServiceFacade{
	/* PMAppService setting */
	private PMAppService pmAppService;
	public void setPmAppService(PMAppService pmAppService) {
		this.pmAppService = pmAppService;
	}

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

	
}
