package com.test.common.applicationservice;

import java.util.List;

import com.test.base.to.BasicCodeBean;
import com.test.base.to.ZipCodeBean;

public interface CommonApplicationService {
	public int getRowCount();
	public List<ZipCodeBean> getZipcodeList(String dong);
	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code);
	public List<BasicCodeBean> getBasicCodeList(int sr, int er);
}
