package com.test.base.applicationservice;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.common.dao.BasicCodeDAO;
import com.test.common.dao.BasicCodeDAOImpl;
import com.test.common.to.BasicCodeBean;
import com.test.hr.emp.dao.EmpDAO;
import com.test.hr.emp.dao.EmpDAOImpl;
import com.test.hr.emp.to.EmpBean;

public class BaseApplicationServiceImpl implements BaseApplicationService {
	private static BaseApplicationService instance = new BaseApplicationServiceImpl();

	public static BaseApplicationService getInstance() {
		return instance;
	}

	protected final Log logger = LogFactory.getLog(getClass());
	private BasicCodeDAO basicCodeDAO = BasicCodeDAOImpl.getInstance();


	public int getRowCount() {
		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getRowCount() - 끝");
		}
		return basicCodeDAO.selectRowCount();

	}
	private EmpDAO empDAO = EmpDAOImpl.getInstance();
	@Override
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException {
		if (logger.isDebugEnabled()) {
			logger.debug("login() - 시작");
		}

		if (logger.isFatalEnabled()) {
			logger.fatal("login()  - 에러");
		}
		return empDAO.selectEmp(empCode);
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er) {
		if (logger.isDebugEnabled()) {
			logger.debug("getcodeList() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getcodeList() - 끝");
		}
		
		return basicCodeDAO.selectBasicCodeList(sr, er, null);
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		if (logger.isDebugEnabled()) {
			logger.debug("getcodeList() - 시작");
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getcodeList() - 끝");
		}
		return basicCodeDAO.selectDetailCodeList(sr, er, code);

	}

}
