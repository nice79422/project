package com.test4th.hr.circumstance.applicationService;

import java.util.List;

import com.test4th.hr.circumstance.dao.SalPaymentDateDAO;
import com.test4th.hr.circumstance.to.SalPaymentDateBean;

public class SalPaymentDateAppServiceImpl implements SalPaymentDateAppService {

	/* SalPaymentsDateDAO setting */
	private SalPaymentDateDAO salPaymentDateDAO;
	public void setSalPaymentDateDAO(SalPaymentDateDAO salPaymentDateDAO) {
		this.salPaymentDateDAO = salPaymentDateDAO;
	}

	/* 해당 귀속년월의 급/상여 지급일및 관련 정보를 조회하는 메서드 */
	@Override
	public List<SalPaymentDateBean> findSalPaymentDateList(String inputedYearMonth) {
		return salPaymentDateDAO.selectSalPaymentDateList(inputedYearMonth);
	}

	
	// 급/상여 지급일 등록및 수정,삭제를 일괄처리하는 메서드 
	@Override
	public void batchSalPaymentDate(List<SalPaymentDateBean> salPaymentDateList) {
		for(SalPaymentDateBean salPaymentDateBean:salPaymentDateList){
			switch(salPaymentDateBean.getStatus()){
				case "insert" : salPaymentDateDAO.insertSalPaymentDate(salPaymentDateBean); break;
				case "update" : salPaymentDateDAO.updateSalPaymentDate(salPaymentDateBean); break;
				case "delete" : salPaymentDateDAO.deleteSalPaymentDate(salPaymentDateBean); break;
			}
		}
	}
	
}
