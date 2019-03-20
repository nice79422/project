package com.test.hr.emp.sf;

import java.util.List;

import com.test.hr.emp.applicationservice.EmpApplicationService;
import com.test.hr.emp.to.EmpBean;

public class EmpServiceFacadeImpl implements EmpServiceFacade {
	
	EmpApplicationService empApplicationService;

	public void setEmpApplicationService(EmpApplicationService empApplicationService) {
		this.empApplicationService = empApplicationService;
	}

	public int getRowCount() {
		int count = empApplicationService.getRowCount();
		return count;
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er , String empCode, String empName) {
		List<EmpBean> bean = empApplicationService.getEmpList(sr, er, empCode, empName);
		return bean;
	}


	public EmpBean findEmp(String empCode) {
		EmpBean empBean = empApplicationService.findEmp(empCode);
		System.out.println("empBean:"+empBean);
		return empBean;
	}

	@Override
	public void registerEmp(EmpBean empBean) {
		empApplicationService.registerEmp(empBean);
	}

	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		empApplicationService.modifyEmp(empBean);
	}

	@Override
	public void removeEmpList(String empCode) {
		empApplicationService.removeEmpList(empCode);
	
	}



}
