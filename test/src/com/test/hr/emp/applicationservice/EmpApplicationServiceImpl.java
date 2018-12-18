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
			logger.debug("getRowCount() - ����");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - ��");
		}

		return empDAO.selectRowCount();
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("getEmpList() - ����");
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
			logger.debug("getEmpList() - ��");
		}
		return bean;
	}

	public EmpBean findEmp(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - ����");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - ��");
		}
		return empDAO.selectEmp(empCode);
	}

	@Override
	public void registerEmp(EmpBean empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("registerEmp() - ����");
		}

		empDAO.insertEmp(empBean);

		if (logger.isDebugEnabled()) {
			logger.debug("registerEmp() - ��");
		}
	}

	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateEmp() - ����");
		}

		for (EmpBean bean : empBean)
			empDAO.updateEmp(bean);

		if (logger.isDebugEnabled()) {
			logger.debug("modifyEmp() - ��");
		}

	}

	@Override
	public void removeEmpList(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeEmpList() - ����");
		}

		empDAO.deleteEmpList(empCode);

		if (logger.isDebugEnabled()) {
			logger.debug("removeEmpList() - ��");
		}
	}
}
