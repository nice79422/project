package com.test4th.hr.circumstance.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.circumstance.to.DeductionTaxBean;
import com.test4th.hr.circumstance.to.IncomeTaxBean;

public class DeductionInsurDAOImpl extends IBatisDAO implements DeductionInsurDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 보험공제관련 정보 목록을 조회하는 메서드 */
	public List<DeductionTaxBean> selectDeductionTaxList() {
		return getSqlMapClientTemplate().queryForList("deductionInsurance.selectDeductionTaxList");
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 소득세 관련정보를 조회하는 메서드 */
	public List<IncomeTaxBean> selectIncomeTaxList() {
		return getSqlMapClientTemplate().queryForList("deductionInsurance.selectIncomeTaxList");
	}
	
	
	
	// 보험공제관련정보를 삭제하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void deleteDeductionTax(DeductionTaxBean deductionTaxBean) {
		getSqlMapClientTemplate().delete("deductionInsurance.deleteDeductionTax",deductionTaxBean);
	}
	
	// 소득세 관련정보를 삭제하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void deleteIncomeTax(IncomeTaxBean incomeTaxBean) {
		getSqlMapClientTemplate().delete("deductionInsurance.deleteIncomeTax",incomeTaxBean);
	}
		

	@SuppressWarnings("deprecation")
	@Override
	// 보험공제관련정보를 추가하는 메서드 
	public void insertDeductionTax(DeductionTaxBean deductionTaxBean) {
		getSqlMapClientTemplate().insert("deductionInsurance.insertDeductionTax",deductionTaxBean);
	}
	
	// 소득세 관련정보를 추가하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void insertIncomeTax(IncomeTaxBean incomeTaxBean) {
		getSqlMapClientTemplate().insert("deductionInsurance.insertIncomeTax",incomeTaxBean);
	}
	

}
