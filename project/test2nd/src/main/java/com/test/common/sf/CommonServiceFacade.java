package com.test.common.sf;

import java.util.List;

import com.test.base.to.BasicCodeBean;


public interface CommonServiceFacade {
	public int getRowCount();
	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code);
	public List<BasicCodeBean> getBasicCodeList(int sr, int er);
}
