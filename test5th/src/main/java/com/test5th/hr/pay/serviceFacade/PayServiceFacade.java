package com.test5th.hr.pay.serviceFacade;

import java.util.List;

import com.test5th.common.exception.ProcedureException;
import com.test5th.hr.pay.to.SalaryInputBean;

public interface PayServiceFacade {
	public List<SalaryInputBean> payCalculate(String paymentsDate, String standardDate) throws ProcedureException;
}
