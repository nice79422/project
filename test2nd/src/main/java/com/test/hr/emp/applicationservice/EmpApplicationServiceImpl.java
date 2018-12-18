package com.test.hr.emp.applicationservice;

import java.util.List;

import com.test.hr.emp.dao.EmpDAO;
import com.test.hr.emp.to.EmpBean;



public class EmpApplicationServiceImpl implements EmpApplicationService {

	private EmpDAO empDAO;

	public void setEmpDAO(EmpDAO empDAO) {
		this.empDAO = empDAO;
	}

	public int getRowCount() {
		return empDAO.selectRowCount();
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName) {
		// TODO Auto-generated method stub
		List<EmpBean> bean = null;
		if (empCode!=null && empName!=null){
			bean = empDAO.selectEmpList(sr, er, empCode, empName);
		}else if (empCode!=null && empName.equals("")){
			bean = empDAO.selectEmpList(sr, er, "ITEM_NAME/" + empName);
		}else if (empCode!=null && empName.equals("")){
			bean = empDAO.selectEmpList(sr, er, "ITEM_CODE/" + empCode);
		}else{
			bean = empDAO.selectEmpList(sr, er);
		}
		return bean;
	}

	public EmpBean findEmp(String empCode) {

		return empDAO.selectEmp(empCode);
	}

	@Override
	public void registerEmp(EmpBean empBean) {
		empDAO.insertEmp(empBean);
	}


	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		for (EmpBean bean : empBean)
			empDAO.updateEmp(bean);
	}

	@Override
	public void removeEmpList(String empCode) {
		empDAO.deleteEmpList(empCode);
	}
}
