package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;

public interface DeductionInsurApplicationService {

	public List<DeductionTaxTO> findDeductionTaxList();
	public List<IncomeTaxTO> findIncomeTaxList();
	
	public void removeDeductionTax(DeductionTaxTO deductionTaxTO);
	public void removeIncomeTaxList(List<IncomeTaxTO> incomeTaxList);
	
	
	public void addDeductionTax(DeductionTaxTO deductionTaxTO);
	public void addIncomeTaxList(List<IncomeTaxTO> incomeTaxList);

}
