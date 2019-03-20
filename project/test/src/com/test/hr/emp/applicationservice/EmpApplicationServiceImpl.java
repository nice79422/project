package com.test.hr.emp.applicationservice;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.hr.emp.dao.EmpDAO;
import com.test.hr.emp.dao.EmpDAOImpl;
import com.test.hr.emp.to.EmpBean;



public class EmpApplicationServiceImpl implements EmpApplicationService {
	protected final Log logger = LogFactory.getLog(getClass());

	private static EmpApplicationService instance = new EmpApplicationServiceImpl();

	public static EmpApplicationService getInstance() {
		return instance;
	}

	private EmpDAO empDAO = EmpDAOImpl.getInstance();

	

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 끝");
		}

		return empDAO.selectRowCount();
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("getEmpList() - 시작");
		}
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

		
	
		if (logger.isDebugEnabled()) {
			logger.debug("getEmpList() - 끝");
		}
		return bean;
	}

	public EmpBean findEmp(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - 끝");
		}
		return empDAO.selectEmp(empCode);
	}

	@Override
	public void registerEmp(EmpBean empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerEmp() - 시작");
		}

		empDAO.insertEmp(empBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerEmp() - 끝");
		}
	}

	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateEmp() - 시작");
		}

		for (EmpBean bean : empBean)
			empDAO.updateEmp(bean);

		if (logger.isDebugEnabled()) {
			logger.debug("modifyEmp() - 끝");
		}

	}

	@Override
	public void removeEmpList(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeEmpList() - 시작");
		}

		empDAO.deleteEmpList(empCode);

		if (logger.isDebugEnabled()) {
			logger.debug("removeEmpList() - 끝");
		}
	}
}
