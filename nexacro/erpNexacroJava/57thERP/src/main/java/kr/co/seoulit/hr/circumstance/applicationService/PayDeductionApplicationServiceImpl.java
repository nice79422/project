package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.circumstance.dao.PayDeductionDAO;
import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;

@Component
public class PayDeductionApplicationServiceImpl implements PayDeductionApplicationService {
	/* PayDeductionDAO setting */
	@Autowired
	private PayDeductionDAO payDeductionDAO;
	

	/* 지급공제 목록을 조회하는 메서드 */
	@Override
	public List<PayDeductionItemTO> findPayDeductionList() {
		return payDeductionDAO.selectPayDeductionList();
	}

	
	// 지급/공제 관련된 항목을 일괄처리하는 메서드 
	@Override
	public void batchPayDeduction(List<PayDeductionItemTO> payDeductionList) {
		for(PayDeductionItemTO payDeductionItemTO:payDeductionList){
			switch(payDeductionItemTO.getStatus()){
				case "inserted" : payDeductionDAO.insertPayDeduction(payDeductionItemTO); break;
				case "updated" : payDeductionDAO.updatePayDeduction(payDeductionItemTO); break;
				case "deleted" : payDeductionDAO.deletePayDeduction(payDeductionItemTO); break;
			}
		}
	}
	
}

