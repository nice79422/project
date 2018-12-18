package com.test.common.sf;

import java.util.List;

import com.test.base.to.BasicCodeBean;
import com.test.common.applicationservice.CommonApplicationService;

public class CommonServiceFacadeImpl implements CommonServiceFacade{
	CommonApplicationService commonApplicationService ;

	public final void setCommonApplicationService(CommonApplicationService commonApplicationService) {
		this.commonApplicationService = commonApplicationService;
	}

	public int getRowCount() {
			int count=commonApplicationService.getRowCount();
			return count;
	}

	public List<BasicCodeBean> getBasicCodeList(int sr, int er){
			List<BasicCodeBean> bean = commonApplicationService.getBasicCodeList(sr, er);
			return bean;
	}

	public List<BasicCodeBean> getDetailCodeList(int sr, int er, String code) {
			List<BasicCodeBean> bean = commonApplicationService.getDetailCodeList(sr, er, code);
			return bean;
	}


}
