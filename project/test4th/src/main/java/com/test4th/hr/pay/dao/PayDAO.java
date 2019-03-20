package com.test4th.hr.pay.dao;

import java.util.List;
import java.util.Map;

import com.test4th.hr.pay.to.SalaryInputBean;

public interface PayDAO {
	public Map<String, Object> payCalculate(String paymentsDate, String  standardDate);
	public List<SalaryInputBean> selectSalaryInputList(String paymentsDate);
}
