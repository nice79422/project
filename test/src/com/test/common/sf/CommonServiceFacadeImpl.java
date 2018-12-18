package com.test.common.sf;

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.test.common.applicationservice.CommonApplicationService;
import com.test.common.applicationservice.CommonApplicationServiceImpl;
import com.test.common.to.BasicCodeBean;
import com.test.common.transaction.DataSourceTransactionManager;

public class CommonServiceFacadeImpl implements CommonServiceFacade{
	private static CommonServiceFacade instance=new CommonServiceFacadeImpl();
	public static CommonServiceFacade getInstance(){
		return instance;
	}
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager=DataSourceTransactionManager.getInstance();
	CommonApplicationService commonApplicationService = CommonApplicationServiceImpl.getInstance();

	public int getRowCount() {
		if(logger.isDebugEnabled()){logger.debug("getRowCount() - 시작");}
		dataSourceTransactionManager.beginTransaction();
		try{
			int count=commonApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getRowCount() - 끝");}
			return count;
		}catch(Exception e){
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er){
		if(logger.isDebugEnabled()){logger.debug("getcodeList() - 시작");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<BasicCodeBean> bean = commonApplicationService.getBasicCodeList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getcodeList() - 끝");}
			return bean;
		}catch(Exception e){
			if(logger.isFatalEnabled()){logger.debug("getcodeList() - 에러");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		if(logger.isDebugEnabled()){logger.debug("getcodeList() - 시작");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<BasicCodeBean> bean = commonApplicationService.getDetailCodeList(sr, er, code);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getcodeList() - 끝");}
			return bean;
		}catch(Exception e){
			if(logger.isFatalEnabled()){logger.debug("getcodeList() - 에러");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	}


