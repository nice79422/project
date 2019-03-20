package com.test.common.applicationservice;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.BasicCodeDAO;
import com.test.common.dao.BasicCodeDAOImpl;
import com.test.common.to.BasicCodeBean;
import com.test.common.to.ZipCodeBean;

public class CommonApplicationServiceImpl implements CommonApplicationService {
	private static CommonApplicationService instance = new CommonApplicationServiceImpl();

	public static CommonApplicationService getInstance() {
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
