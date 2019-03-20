package com.test4th.hr.pay.service;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.pay.to.SalaryInputBean;

public interface PayServiceFacade {
	public List<SalaryInputBean> payCalculate(String paymentsDate, String standardDate) throws ProcedureException;
}
