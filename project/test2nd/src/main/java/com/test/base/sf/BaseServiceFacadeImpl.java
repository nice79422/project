package com.test.base.sf;

import java.util.List;

import com.test.base.applicationservice.BaseApplicationService;
import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.base.to.BasicCodeBean;
import com.test.hr.emp.to.EmpBean;




public class BaseServiceFacadeImpl implements BaseServiceFacade{
	BaseApplicationService baseApplicationService;

	public void setBaseApplicationService(BaseApplicationService baseApplicationService) {
		this.baseApplicationService = baseApplicationService;
	}

	
	@Override
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException {
		return baseApplicationService.login(empCode,pw);
		}
		
	
	public int getRowCount() {
		int count=baseApplicationService.getRowCount();
		return count;
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er){
		List<BasicCodeBean> bean = baseApplicationService.getBasicCodeList(sr, er);
		return bean;
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		List<BasicCodeBean> bean = baseApplicationService.getDetailCodeList(sr, er, code);
		return bean;
	}


}
