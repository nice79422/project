package com.test.base.applicationservice;

import java.util.List;

import com.test.base.dao.BasicCodeDAO;
import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.base.to.BasicCodeBean;
import com.test.hr.emp.dao.EmpDAO;
import com.test.hr.emp.to.EmpBean;

public class BaseApplicationServiceImpl implements BaseApplicationService {
	private BasicCodeDAO basicCodeDAO;
	private EmpDAO empDAO;

	
	public void setBasicCodeDAO(BasicCodeDAO basicCodeDAO) {
		this.basicCodeDAO = basicCodeDAO;
	}

	
	
	public void setEmpDAO(EmpDAO empDAO) {
		this.empDAO = empDAO;
	}
	
	
	public int getRowCount() {
		return basicCodeDAO.selectRowCount();
	}
	@Override
	public EmpBean login(String empCode, String pw) throws IdNotFoundException, PwMissMatchException {
		EmpBean empBean = empDAO.selectEmp(empCode);
		
		if (empBean == null) {
			
			throw new IdNotFoundException("아이디를 찾을수 없습니다.");
		} else {
			if (empBean.getPassword().equals(pw)) {
				return empBean;
			} else {
				
				throw new PwMissMatchException("비밀번호 오류입니다.");
			}
		}
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		return basicCodeDAO.selectDetailCodeList(sr, er, code);

	}



	@Override
	public List<BasicCodeBean> getBasicCodeList(int sr, int er) {
		// TODO Auto-generated method stub
		return basicCodeDAO.selectBasicCodeList(sr, er, null);
	}
}
