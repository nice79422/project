package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;

public interface PayDeductionApplicationService {

	public List<PayDeductionItemTO> findPayDeductionList();

	public void batchPayDeduction(List<PayDeductionItemTO> payDeductionList);

}
