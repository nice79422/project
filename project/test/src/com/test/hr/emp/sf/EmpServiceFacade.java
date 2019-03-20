package com.test.hr.emp.sf;

import java.util.List;

import com.test.exception.IdNotFoundException;
import com.test.exception.PwMissMatchException;
import com.test.hr.emp.to.EmpBean;

public interface EmpServiceFacade {
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException;
	public int getRowCount();
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName);
	public EmpBean findEmp(String empCode);
	public void registerEmp(EmpBean empBean);
	public void modifyEmp(List<EmpBean> empBean);
	public void removeEmpList(String empCode);
}

