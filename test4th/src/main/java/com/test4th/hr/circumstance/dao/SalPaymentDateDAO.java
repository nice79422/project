package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.hr.circumstance.to.SalPaymentDateBean;

public interface SalPaymentDateDAO {
	public List<SalPaymentDateBean> selectSalPaymentDateList(String inputedYearMonth);
	
	
	public void insertSalPaymentDate(SalPaymentDateBean salPaymentDateBean);
	public void updateSalPaymentDate(SalPaymentDateBean salPaymentDateBean);
	public void deleteSalPaymentDate(SalPaymentDateBean salPaymentDateBean);
	
}
