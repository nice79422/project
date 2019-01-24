package com.test5th.hr.pay.applicationService;

import java.util.List;

import com.test5th.common.exception.ProcedureException;
import com.test5th.hr.pay.to.SalaryInputBean;

public interface PayAppService {
	public List<SalaryInputBean> payCalculate(String paymentsDate, String standardDate) throws ProcedureException;
}
