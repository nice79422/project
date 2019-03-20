package com.test.hr.emp.dao;

import java.util.List;

import com.test.hr.emp.to.EmpBean;

public interface EmpDAO {
	public int selectRowCount();
	public void insertEmp(EmpBean emp);
	public List<EmpBean> selectEmpList(int sr, int er);
	public int selectDbcount();
	public EmpBean selectEmp(String empCode); 
    public void deleteEmpList(String empCode);
	public void updateEmp(EmpBean emp);
/*	public List<EmpBean> selectEmpList(int sr, int er, String empCode, String empName); 
	public List<EmpBean> selectEmpList(int sr, int er, String searchWord);*/
	public List<EmpBean> selectAllSearchEmpList(int sr, int er, String empCode, String empName);
	public List<EmpBean> selectCodeSearchEmpList(int sr, int er, String searchWord);
	public List<EmpBean> selectNameSearchEmpList(int sr, int er, String searchWord);
}
