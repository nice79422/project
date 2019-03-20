package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.PayStepTO;

public interface PayStepApplicationService {

	public List<PayStepTO> findPayStepList();	
	public void batchPayStepList(List<PayStepTO> payStepList);	
}
