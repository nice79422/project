package com.test.hr.emp.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.base.dao.IBatisDAO;
import com.test.hr.emp.to.EmpBean;

public class EmpDAOImpl extends IBatisDAO implements EmpDAO {


	@SuppressWarnings("deprecation")
	public int selectRowCount() {
		return (int) getSqlMapClientTemplate().queryForObject("emp.selectRowCount");
	}



	@SuppressWarnings("deprecation")
	@Override
	public void insertEmp(EmpBean emp) {
		getSqlMapClientTemplate().update("emp.insertEmp", emp);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmpBean> selectEmpList(int sr, int er) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		return (List<EmpBean>) getSqlMapClientTemplate().queryForList("emp.selectEmpList", map);
	}	

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmpBean> selectAllSearchEmpList(int sr, int er, String empCode, String empName) {
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("empCode", empCode);
		map.put("empName", empName);
		return (List<EmpBean>) getSqlMapClientTemplate().queryForList("emp.selectAllSearchEmpList", map);

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmpBean> selectCodeSearchEmpList(int sr, int er, String searchWord) {

		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("searchWord", searchWord);
		return (List<EmpBean>) getSqlMapClientTemplate().queryForList("emp.selectCodeSearchEmpList", map);

	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<EmpBean> selectNameSearchEmpList(int sr, int er, String searchWord) {

		Map<String, Object> map = new HashMap<>();
		map.put("startRow", sr);
		map.put("endRow", er);
		map.put("searchWord", searchWord);
		return (List<EmpBean>) getSqlMapClientTemplate().queryForList("emp.selectNameSearchEmpList", map);

	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public int selectDbcount() {
		return (int) getSqlMapClientTemplate().queryForObject("emp.selectDbCount");
			}

	
	@SuppressWarnings("deprecation")
	@Override
	public EmpBean selectEmp(String empCode) {
		
		return (EmpBean)getSqlMapClientTemplate().queryForObject("emp.selectEmp",empCode);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void updateEmp(EmpBean emp) {
		getSqlMapClientTemplate().update("emp.updateEmp", emp);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteEmpList(String empCode) {
		getSqlMapClientTemplate().delete("emp.deleteEmp", empCode);
	}


}

