package com.test.common.applicationservice;

import java.util.List;
import com.test.common.to.BasicCodeBean;


public interface CommonApplicationService {
	public int getRowCount();
	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code);
	public List<BasicCodeBean> getBasicCodeList(int sr, int er);
}
