package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.to.PayDeductionBean;

public interface PayDeductionAppService {
	public List<PayDeductionBean> findPayDeductionList();
	public void batchPayDeduction(List<PayDeductionBean> payDeductionList);
}
