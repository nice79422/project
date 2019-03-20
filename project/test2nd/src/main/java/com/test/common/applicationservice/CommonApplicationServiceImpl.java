package com.test.common.applicationservice;

import java.util.List;

import com.test.base.dao.BasicCodeDAO;

import com.test.base.to.BasicCodeBean;



public class CommonApplicationServiceImpl implements CommonApplicationService {
	private BasicCodeDAO basicCodeDAO ;


	public final void setBasicCodeDAO(BasicCodeDAO basicCodeDAO) {
		this.basicCodeDAO = basicCodeDAO;
	}



	public int getRowCount() {
		return basicCodeDAO.selectRowCount();
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er) {
		return basicCodeDAO.selectBasicCodeList(sr, er, null);
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		return basicCodeDAO.selectDetailCodeList(sr, er, code);
	}


}
