package com.test4th.hr.pay.service;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.pay.applicationService.PayAppService;
import com.test4th.hr.pay.to.SalaryInputBean;

public class PayServiceFacadeImpl implements PayServiceFacade{
	/* PayAppService setting  */
	private PayAppService payAppService;
	public void setPayAppService(PayAppService payAppService) {
		this.payAppService = payAppService;
	}

	/* 급여를 계산하는 메서드 */
	@Override
	public List<SalaryInputBean> payCalculate(String paymentsDate, String standardDate) throws ProcedureException{
		return payAppService.payCalculate(paymentsDate, standardDate);
	}
}
