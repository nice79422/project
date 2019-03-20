package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.dao.PayDeductionDAO;
import com.test4th.hr.circumstance.to.PayDeductionBean;

public class PayDeductionAppServiceImpl implements PayDeductionAppService {
	/* PayDeductionDAO setting */
    private PayDeductionDAO payDeductionDAO;
	public void setPayDeductionDAO(PayDeductionDAO payDeductionDAO) {
		this.payDeductionDAO = payDeductionDAO;
	}

	/* 지급공제 목록을 조회하는 메서드 */
	@Override
	public List<PayDeductionBean> findPayDeductionList() {
		return payDeductionDAO.selectPayDeductionList();
	}

	
	// 지급/공제 관련된 항목을 일괄처리하는 메서드 
	@Override
	public void batchPayDeduction(List<PayDeductionBean> payDeductionList) {
		for(PayDeductionBean payDeductionBean:payDeductionList){
			switch(payDeductionBean.getStatus()){
				case "insert" : payDeductionDAO.insertPayDeduction(payDeductionBean); break;
				case "update" : payDeductionDAO.updatePayDeduction(payDeductionBean); break;
				case "delete" : payDeductionDAO.deletePayDeduction(payDeductionBean); break;
			}
		}
	}
	
}
