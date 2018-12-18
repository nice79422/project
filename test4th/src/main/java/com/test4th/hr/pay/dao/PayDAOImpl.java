package com.test4th.hr.pay.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.pay.to.SalaryInputBean;

public class PayDAOImpl extends IBatisDAO implements PayDAO {

	@SuppressWarnings("deprecation")
	@Override
	/* 급여를 계산하는 메서드 */
	public Map<String, Object> payCalculate(String paymentDate, String standardDate) {
		Map<String, Object> map = new HashMap<>();
		map.put("paymentDate", paymentDate);
		map.put("standardDate", standardDate);
		System.out.println(paymentDate);
		System.out.println(standardDate);
		getSqlMapClientTemplate().queryForObject("pay.payCalculate", map);
		return map;
	}

	/* 입력된 급여를 조회하는 메서드 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<SalaryInputBean> selectSalaryInputList(String paymentDate) {
		return getSqlMapClientTemplate().queryForList("pay.selectSalaryInputList", paymentDate);
	}
}
