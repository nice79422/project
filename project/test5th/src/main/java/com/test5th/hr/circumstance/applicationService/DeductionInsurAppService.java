package com.test5th.hr.circumstance.applicationService;

import java.util.List;

import com.test5th.hr.circumstance.to.DeductionTaxBean;
import com.test5th.hr.circumstance.to.IncomeTaxBean;

public interface DeductionInsurAppService {
	public List<DeductionTaxBean> findDeductionTaxList();
	public List<IncomeTaxBean> findIncomeTaxList();
	
	public void removeDeductionTax(DeductionTaxBean deductionTaxBean);
	public void removeIncomeTaxList(List<IncomeTaxBean> incomeTaxList);
	
	
	public void addDeductionTax(DeductionTaxBean deductionTaxBean);
	public void addIncomeTaxList(List<IncomeTaxBean> incomeTaxList);

	
}
