package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.SalPaymentDateDAO;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;

@Component
public class SalPaymentDateApplicationServiceImpl implements SalPaymentDateApplicationService {

	/* SalPaymentsDateDAO setting */
	@Autowired
	private SalPaymentDateDAO salPaymentDateDAO;
	

	/* 해당 귀속년월의 급/상여 지급일및 관련 정보를 조회하는 메서드 */
	@Override
	public List<SalPaymentDateTO> findSalPaymentDateList(String inputedYearMonth) {
		return salPaymentDateDAO.selectSalPaymentDateList(inputedYearMonth);
	}

	
	// 급/상여 지급일 등록및 수정,삭제를 일괄처리하는 메서드 
	@Override
	public void batchSalPaymentDate(List<SalPaymentDateTO> salPaymentDateList) {
		for(SalPaymentDateTO salPaymentDateTO:salPaymentDateList){
			switch(salPaymentDateTO.getStatus()){
				case "inserted" : salPaymentDateDAO.insertSalPaymentDate(salPaymentDateTO); break;
				case "updated" : salPaymentDateDAO.updateSalPaymentDate(salPaymentDateTO); break;
				case "deleted" : salPaymentDateDAO.deleteSalPaymentDate(salPaymentDateTO); break;
			}
		}
	}
	
}
