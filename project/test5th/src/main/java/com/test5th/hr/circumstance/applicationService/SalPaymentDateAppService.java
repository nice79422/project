package com.test5th.hr.circumstance.applicationService;

import java.util.List;

import com.test5th.hr.circumstance.to.SalPaymentDateBean;

public interface SalPaymentDateAppService {
	public List<SalPaymentDateBean> findSalPaymentDateList(String inputedYearMonth);
	public void batchSalPaymentDate(List<SalPaymentDateBean> salPaymentsDateList);
}
