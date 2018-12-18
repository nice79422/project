package com.test.hr.emp.applicationservice;

import java.util.List;

import com.test.hr.emp.to.EmpBean;


public interface EmpApplicationService {

	public int getRowCount();
	
	public EmpBean findEmp(String empCode);
	public void registerEmp(EmpBean empBean);
	public void modifyEmp(List<EmpBean> empBean);
	public void removeEmpList(String empCode);
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName);
/*	public List<EmpBean> getEmpList(int sr, int er);*/

	
}
