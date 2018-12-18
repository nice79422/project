package com.test.base.sf;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.common.applicationservice.CommonApplicationService;
import com.test.common.applicationservice.CommonApplicationServiceImpl;
import com.test.common.to.BasicCodeBean;
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.hr.emp.applicationservice.EmpApplicationService;
import com.test.hr.emp.applicationservice.EmpApplicationServiceImpl;
import com.test.hr.emp.to.EmpBean;


public class BaseServiceFacadeImpl implements BaseServiceFacade{
	private static BaseServiceFacade instance=new BaseServiceFacadeImpl();
	public static BaseServiceFacade getInstance(){
		return instance;
	}
	protected final Log logger = LogFactory.getLog(getClass());
	DataSourceTransactionManager dataSourceTransactionManager=DataSourceTransactionManager.getInstance();
	CommonApplicationService commonApplicationService = CommonApplicationServiceImpl.getInstance();

	
	@Override
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException {
		if (logger.isDebugEnabled()) {
			logger.debug("login() - ����");
		}
		EmpApplicationService empApplicationService = EmpApplicationServiceImpl.getInstance();
		dataSourceTransactionManager.beginTransaction();
		try {
			
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
		if(logger.isDebugEnabled()){logger.debug("getRowCount() - ����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			int count=commonApplicationService.getRowCount();
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getRowCount() - ��");}
			return count;
		}catch(Exception e){
			e.printStackTrace();
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er){
		if(logger.isDebugEnabled()){logger.debug("getcodeList() - ����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			
			List<BasicCodeBean> bean = commonApplicationService.getBasicCodeList(sr, er);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getcodeList() - ��");}
			return bean;
		}catch(Exception e){
			if(logger.isFatalEnabled()){logger.debug("getcodeList() - ����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		if(logger.isDebugEnabled()){logger.debug("getcodeList() - ����");}
		dataSourceTransactionManager.beginTransaction();
		try{
			List<BasicCodeBean> bean = commonApplicationService.getDetailCodeList(sr, er, code);
			dataSourceTransactionManager.commitTransaction();
			if(logger.isDebugEnabled()){logger.debug("getcodeList() - ��");}
			return bean;
		}catch(Exception e){
			if(logger.isFatalEnabled()){logger.debug("getcodeList() - ����");}
			dataSourceTransactionManager.rollbackTransaction();
			throw e;
		}
	}
	}


