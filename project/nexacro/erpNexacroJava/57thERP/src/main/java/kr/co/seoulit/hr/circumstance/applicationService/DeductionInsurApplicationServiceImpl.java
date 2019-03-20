package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.DeductionInsurDAO;
import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;

@Component
public class DeductionInsurApplicationServiceImpl implements DeductionInsurApplicationService{

	@Autowired
	private DeductionInsurDAO deductionInsurDAO;
	
	
	@Override
	public List<DeductionTaxTO> findDeductionTaxList() {
		return deductionInsurDAO.selectDeductionTaxList();
	}

	@Override
	public List<IncomeTaxTO> findIncomeTaxList() {
		return deductionInsurDAO.selectIncomeTaxList();
	}

	@Override
	public void removeDeductionTax(DeductionTaxTO deductionTaxTO) {
		deductionInsurDAO.deleteDeductionTax(deductionTaxTO);
		
	}

	@Override
	public void removeIncomeTaxList(List<IncomeTaxTO> incomeTaxList) {
		for(IncomeTaxTO incomeTax:incomeTaxList){
			deductionInsurDAO.deleteIncomeTax(incomeTax);
			}
	}

	@Override
	public void addDeductionTax(DeductionTaxTO deductionTaxTO) {
		deductionInsurDAO.insertDeductionTax(deductionTaxTO);
		
	}

	@Override
	public void addIncomeTaxList(List<IncomeTaxTO> incomeTaxList) {
		for(IncomeTaxTO incomeTax:incomeTaxList){
			deductionInsurDAO.insertIncomeTax(incomeTax);
		}
	}	
}
