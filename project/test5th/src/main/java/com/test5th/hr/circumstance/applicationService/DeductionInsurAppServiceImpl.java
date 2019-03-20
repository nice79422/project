package com.test5th.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.hr.circumstance.dao.DeductionInsurDAO;
import com.test5th.hr.circumstance.to.DeductionTaxBean;
import com.test5th.hr.circumstance.to.IncomeTaxBean;

@Component
public class DeductionInsurAppServiceImpl implements DeductionInsurAppService {
	/* DeductionInsurDAO  setting */
	@Autowired
	private DeductionInsurDAO deductionInsurDAO;
	

	/* 보험공제관련 정보를 조회하는 메서드 */
	@Override
	public List<DeductionTaxBean> findDeductionTaxList() {
		return deductionInsurDAO.selectDeductionTaxList();
	}

	/* 소득세 관련정보를 조회하는 메서드 */
	@Override
	public List<IncomeTaxBean> findIncomeTaxList() {
		return deductionInsurDAO.selectIncomeTaxList();
	}

	
	// 보험공제관련부분을 삭제하는 메서드 
	@Override
	public void removeDeductionTax(DeductionTaxBean deductionTaxBean) {
		deductionInsurDAO.deleteDeductionTax(deductionTaxBean);
	}
	
	//소득세관련부분을 삭제하는 메서드 
	@Override
	public void removeIncomeTaxList(List<IncomeTaxBean> incomeTaxList) {
		for(IncomeTaxBean incomeTax:incomeTaxList){
		deductionInsurDAO.deleteIncomeTax(incomeTax);
		}
	}
		
		
	
	// 보험공제관련 정보를 추가하는 메서드 
	@Override
	public void addDeductionTax(DeductionTaxBean deductionTaxBean) {
		deductionInsurDAO.insertDeductionTax(deductionTaxBean);
	}
	
	// 소득세관련 정보를 추가하하는 메서드 
	@Override
	public void addIncomeTaxList(List<IncomeTaxBean> incomeTaxList) {
		for(IncomeTaxBean incomeTax:incomeTaxList){
			deductionInsurDAO.insertIncomeTax(incomeTax);
		}
	}


	
}
