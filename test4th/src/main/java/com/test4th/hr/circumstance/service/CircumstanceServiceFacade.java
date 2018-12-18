package com.test4th.hr.circumstance.service;

import java.util.List;
import java.util.Map;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.circumstance.to.AnnualBean;
import com.test4th.hr.circumstance.to.BaseWorkTimeBean;
import com.test4th.hr.circumstance.to.DeductionTaxBean;
import com.test4th.hr.circumstance.to.HobongBean;
import com.test4th.hr.circumstance.to.HolidayBean;
import com.test4th.hr.circumstance.to.IncomeTaxBean;
import com.test4th.hr.circumstance.to.PayDeductionBean;
import com.test4th.hr.circumstance.to.SalPaymentDateBean;

public interface CircumstanceServiceFacade {
	/*호봉을 구해옴*/
	public List<HobongBean> findHobongList();
	/*호봉 일괄저장*/
	public void batchHobong(List<HobongBean> hobongList);
	/*기본 근무시간 설정 가져오기*/
	public List<BaseWorkTimeBean> findBaseWorkTimeList();
	
	/*기본 근무 시간 추가 수정*/
	public void addBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean);
	public void editBaseWorkTime(BaseWorkTimeBean baseWorkTimeBean);
	
	/*휴일 관리 */
	public List<HolidayBean> findHolidayList(Map<String, Object> dateList);
	public void batchHoliday(List<HolidayBean> holidayList);
	
	/*연차정보 구하기*/
	public List<AnnualBean> findAnnualList();
	public List<AnnualBean> createAnnual(String standardYear,String empCode,String hireDate) throws ProcedureException;
	public void batchAnnual(List<AnnualBean> annualList);
	
	/*연차신청 후 변경 사항 반영*/
	public List<AnnualBean> editAnnulMgt(String standardYear, String empCode, String days, String restDays);
	
	/*소득세율 4대보험 부담률 조회*/
	public List<DeductionTaxBean> findDeductionTaxList();
	public List<IncomeTaxBean> findIncomeTaxList();
	
	/*급여공제항목 삭제*/
	public void removeDeductionTax(DeductionTaxBean deductionsTaxBean);
	public void removeIncomeTaxList(List<IncomeTaxBean> incomeTaxList);
	
	/*금여공제 등록 */
	public void addDeductionTax(DeductionTaxBean deductionsTaxBean);
	public void addIncomeTaxList(List<IncomeTaxBean> incomeTaxList);
	
	/*지급공제 호출 수정 */
	
	public List<PayDeductionBean> findPayDeductionList();
	public void batchPayDeduction(List<PayDeductionBean> payDeductionList);
	
	/* 급여 상여 지급 일자 */
	public List<SalPaymentDateBean>findSalPaymentDateList(String inputedYearMonth);
	public void batchSalPaymentDate(List<SalPaymentDateBean> salPaymentDateList);
	
}
