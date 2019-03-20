package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.SalPaymentDateBean;

public class SalPaymentDateDAOImpl extends IBatisDAO implements SalPaymentDateDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 해당귀속년월의 급/상여 지급일및 관련정보를 조회하는 메서드 */
	public List<SalPaymentDateBean> selectSalPaymentDateList(String inputedYearMonth) {
		return getSqlMapClientTemplate().queryForList("salPaymentDate.selectSalPaymentDateList",inputedYearMonth);
	}

	
	
	@SuppressWarnings("deprecation")
	@Override
	// 급/상여 지급일 및 관련정보를 등록하는 메서드 
	public void insertSalPaymentDate(SalPaymentDateBean salPaymentDateBean) {
		getSqlMapClientTemplate().insert("salPaymentDate.insertSalPaymentDate",salPaymentDateBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	// 급/상여 지급일 및 관련정보를 수정하는 메서드 
	public void updateSalPaymentDate(SalPaymentDateBean salPaymentDateBean) {
		getSqlMapClientTemplate().update("salPaymentDate.updateSalPaymentDate",salPaymentDateBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	// 급/상여 지급일 및 관련정보를 삭제하는 메서드 
	public void deleteSalPaymentDate(SalPaymentDateBean salPaymentDateBean) {
		getSqlMapClientTemplate().delete("salPaymentDate.deleteSalPaymentDate",salPaymentDateBean);
	}
	
	
}
