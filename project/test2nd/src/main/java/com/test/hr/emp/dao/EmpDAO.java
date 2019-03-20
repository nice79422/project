package com.test.hr.emp.dao;

import java.util.List;

import com.test.common.dao.DataAccessException;
import com.test.hr.emp.to.EmpBean;

public interface EmpDAO {
	public int selectRowCount();
	public void insertEmp(EmpBean emp) throws DataAccessException ;
	public List<EmpBean> selectEmpList(int sr, int er) throws DataAccessException ;
	public int selectDbcount() throws DataAccessException;
	public EmpBean selectEmp(String empCode) throws DataAccessException ;
    public void deleteEmpList(String empCode) throws DataAccessException;
	public void updateEmp(EmpBean emp) throws DataAccessException;
	public List<EmpBean> selectEmpList(int sr, int er, String empCode, String empName) throws DataAccessException;
	public List<EmpBean> selectEmpList(int sr, int er, String searchWord) throws DataAccessException;
}
