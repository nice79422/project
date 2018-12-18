package com.test.base.applicationservice;

import java.util.List;

import com.test.base.dao.BasicCodeDAO;
import com.test.base.dao.ZipCodeDAO;
import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.base.to.BasicCodeBean;
import com.test.base.to.ZipCodeBean;
import com.test.hr.emp.dao.EmpDAO;
import com.test.hr.emp.to.EmpBean;

public class BaseApplicationServiceImpl implements BaseApplicationService {
	private BasicCodeDAO basicCodeDAO;
	private ZipCodeDAO zipCodeDAO;
	private EmpDAO empDAO;

	
	public void setBasicCodeDAO(BasicCodeDAO basicCodeDAO) {
		this.basicCodeDAO = basicCodeDAO;
	}

	public void setZipCodeDAO(ZipCodeDAO zipCodeDAO) {
		this.zipCodeDAO = zipCodeDAO;
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
			
			throw new IdNotFoundException("�ش� ���̵� �����ϴ�.");
		} else {
			if (empBean.getPassword().equals(pw)) {
				return empBean;
			} else {
				
				throw new PwMissMatchException("��й�ȣ�� Ȯ���� �ּ���.");
			}
		}
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		return basicCodeDAO.selectDetailCodeList(sr, er, code);

	}

	public List<ZipCodeBean> getZipcodeList(String dong) {
		return zipCodeDAO.selectZipcodeList(dong);

	}

	@Override
	public List<BasicCodeBean> getBasicCodeList(int sr, int er) {
		// TODO Auto-generated method stub
		return basicCodeDAO.selectBasicCodeList(sr, er);
	}
}
