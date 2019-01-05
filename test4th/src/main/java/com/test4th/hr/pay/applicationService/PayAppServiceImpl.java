package com.test4th.hr.pay.applicationService;

import java.util.List;
import java.util.Map;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.pay.dao.PayDAO;
import com.test4th.hr.pay.to.SalaryInputBean;

public class PayAppServiceImpl implements PayAppService {
	/*  PayDAO setting */
	private PayDAO payDAO;
	public void setPayDAO(PayDAO payDAO) {
		this.payDAO = payDAO;
	}

	/* 급여를 계산하는 메서드 */
	@Override
	public List<SalaryInputBean> payCalculate(String paymentDate, String standardDate) throws ProcedureException {
		Map<String, Object> map = payDAO.payCalculate(paymentDate, standardDate);
		
		if(map.get("errorCode")!=null) {
	        int errorCode = Integer.parseInt((String) map.get("errorCode"));
		if (errorCode < 0) {
			throw new ProcedureException((String) map.get("errorMsg"));
		}
		}
		return payDAO.selectSalaryInputList(paymentDate); // 급여 조회 
	}
}
