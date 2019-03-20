package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.List;

import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;

public interface SalPaymentDateApplicationService {
	public List<SalPaymentDateTO> findSalPaymentDateList(String inputedYearMonth);
	public void batchSalPaymentDate(List<SalPaymentDateTO> salPaymentsDateList);
}
