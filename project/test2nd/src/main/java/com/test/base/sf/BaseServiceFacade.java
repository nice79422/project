package com.test.base.sf;

import java.util.List;

import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.base.to.BasicCodeBean;
import com.test.hr.emp.to.EmpBean;

public interface BaseServiceFacade {
	public int getRowCount();

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code);
	public List<BasicCodeBean> getBasicCodeList(int sr, int er);
	
	public EmpBean login(String empCode, String pw)throws IdNotFoundException, PwMissMatchException;;
	
}
