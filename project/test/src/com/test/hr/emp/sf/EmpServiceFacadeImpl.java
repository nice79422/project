package com.test.hr.emp.sf;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.transaction.DataSourceTransactionManager;
import com.test.exception.IdNotFoundException;
import com.test.exception.PwMissMatchException;
import com.test.hr.emp.applicationservice.EmpApplicationService;
import com.test.hr.emp.applicationservice.EmpApplicationServiceImpl;
import com.test.hr.emp.to.EmpBean;

public class EmpServiceFacadeImpl implements EmpServiceFacade {
	protected final Log logger = LogFactory.getLog(getClass());
	private static EmpServiceFacade instance = new EmpServiceFacadeImpl();

	public static EmpServiceFacade getInstance() {
		return instance;
	}

	EmpApplicationService empApplicationService = EmpApplicationServiceImpl.getInstance();

	DataSourceTransactionManager dataSourceTransactionManager = DataSourceTransactionManager.getInstance();

	@Override
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException {
		if (logger.isDebugEnabled()) {
			logger.debug("login() - ����");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			System.out.println("�α��θ޼���");
			EmpBean emp = empApplicationService.findEmp(empCode);

			if (emp == null) {
				throw new IdNotFoundException("�ش� ���̵� �����ϴ�.");
			} else {
				if (emp.getPassword().equals(pw)) {
					dataSourceTransactionManager.commitTransaction();
					return emp;
				} else {
					throw new PwMissMatchException("��й�ȣ�� Ȯ���� �ּ���.");
				}
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("login()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			int count = empApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount() - ��");
			}
			return count;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("getEmpList() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<EmpBean> bean = empApplicationService.getEmpList(sr, er, empCode, empName);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getEmpList() - ��");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getEmpList()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}


	public EmpBean findEmp(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		EmpBean v = empApplicationService.findEmp(empCode);
		dataSourceTransactionManager.commitTransaction();
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - ��");
		}
		return v;
	}


	@Override
	public void registerEmp(EmpBean empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("addEmp() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.registerEmp(empBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("addEmp() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("addEmp()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateEmp() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.modifyEmp(empBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyEmp() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyEmp()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void removeEmpList(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEmpList() - ����");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.removeEmpList(empCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("deleteEmpList() - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("deleteEmpList()  - ����");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

}
