package com.test4th.hr.pay.applicationService;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.pay.to.SalaryInputBean;

public interface PayAppService {
	public List<SalaryInputBean> payCalculate(String paymentsDate, String standardDate) throws ProcedureException;
}
