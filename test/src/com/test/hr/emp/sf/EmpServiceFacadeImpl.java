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
			logger.debug("login() - 시작");
		}

		dataSourceTransactionManager.beginTransaction();
		try {
			System.out.println("로그인메서드");
			EmpBean emp = empApplicationService.findEmp(empCode);

			if (emp == null) {
				throw new IdNotFoundException("해당 아이디가 없습니다.");
			} else {
				if (emp.getPassword().equals(pw)) {
					dataSourceTransactionManager.commitTransaction();
					return emp;
				} else {
					throw new PwMissMatchException("비밀번호를 확인해 주세요.");
				}
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("login()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			int count = empApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getRowCount() - 끝");
			}
			return count;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getRowCount()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public List<EmpBean> getEmpList(int sr, int er, String empCode, String empName) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("getEmpList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			List<EmpBean> bean = empApplicationService.getEmpList(sr, er, empCode, empName);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("getEmpList() - 끝");
			}
			return bean;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getEmpList()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}


	public EmpBean findEmp(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		EmpBean v = empApplicationService.findEmp(empCode);
		dataSourceTransactionManager.commitTransaction();
		if (logger.isDebugEnabled()) {
			logger.debug("findEmp() - 끝");
		}
		return v;
	}


	@Override
	public void registerEmp(EmpBean empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("addEmp() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.registerEmp(empBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("addEmp() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("addEmp()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void modifyEmp(List<EmpBean> empBean) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateEmp() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.modifyEmp(empBean);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("modifyEmp() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyEmp()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	@Override
	public void removeEmpList(String empCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("deleteEmpList() - 시작");
		}
		dataSourceTransactionManager.beginTransaction();
		try {
			empApplicationService.removeEmpList(empCode);
			dataSourceTransactionManager.commitTransaction();
			if (logger.isDebugEnabled()) {
				logger.debug("deleteEmpList() - 끝");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("deleteEmpList()  - 에러");
			}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

}
