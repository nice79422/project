package com.test.common.applicationservice;

import java.util.List;

import com.test.base.dao.BasicCodeDAO;
import com.test.base.dao.ZipCodeDAO;
import com.test.base.to.BasicCodeBean;
import com.test.base.to.ZipCodeBean;



public class CommonApplicationServiceImpl implements CommonApplicationService {
	private BasicCodeDAO basicCodeDAO ;
	private ZipCodeDAO zipCodeDAO ;

	public final void setBasicCodeDAO(BasicCodeDAO basicCodeDAO) {
		this.basicCodeDAO = basicCodeDAO;
	}

	public final void setZipCodeDAO(ZipCodeDAO zipCodeDAO) {
		this.zipCodeDAO = zipCodeDAO;
	}

	public int getRowCount() {
		return basicCodeDAO.selectRowCount();
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er) {
		return basicCodeDAO.selectBasicCodeList(sr, er);
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
		return basicCodeDAO.selectDetailCodeList(sr, er, code);
	}

	public List<ZipCodeBean> getZipcodeList(String dong) {
		return zipCodeDAO.selectZipcodeList(dong);

	}
}
