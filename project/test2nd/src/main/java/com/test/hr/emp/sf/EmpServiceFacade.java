package com.test.hr.emp.sf;

import java.util.List;

import com.test.hr.emp.to.EmpBean;

public interface EmpServiceFacade {
	
	public int getRowCount();
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName);
	public EmpBean findEmp(String empCode);
	public void registerEmp(EmpBean empBean);
	public void modifyEmp(List<EmpBean> empBean);
	public void removeEmpList(String empCode);
}

