package kr.co.seoulit.hr.circumstance.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.hr.circumstance.to.BaseWorkTimeTO;
import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;
import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;
import kr.co.seoulit.hr.circumstance.to.PayStepTO;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;
import kr.co.seoulit.hr.circumstance.to.SudangInfoTO;

public interface CircumstanceServiceFacade {

	/* 호봉 관리 */
	public List<PayStepTO> findPayStepList();

	public void batchPayStepList(List<PayStepTO> payStepList);

	/* 휴일 관리 */
	public List<HolidayTO> findHolidayList(HashMap<String, Object> dateList);
	public void batchHoliday(List<HolidayTO> holidayList);
	public List<HolidayTO> findHolidayListAll();
	public List<HolidayTO> addHoliday(HashMap<String, Object> map) throws ProcedureException;

	/*연차정보 구하기*/
	public List<AnnualLeaveTO> findAnnualLeaveList();
	public List<AnnualLeaveTO> createAnnualLeave(HashMap<String, Object> map) throws ProcedureException;
	public void batchAnnualLeave(List<AnnualLeaveTO> annualLeaveList);

	/*연차신청 후 변경 사항 반영*/
	public List<AnnualLeaveTO> editAnnualLeaveMgt(HashMap<String, Object> map);

	/* 급여 상여 지급 일자*/
	public List<SalPaymentDateTO> findSalPaymentDateList(String inputedYearMonth);
	public void batchSalPaymentDate(List<SalPaymentDateTO> salPaymentDateList);

	/* 지급 공제 항목 등록*/
	public List<PayDeductionItemTO> findPayDeductionList();
	public void batchPayDeduction(List<PayDeductionItemTO> payDeductionList);

	/*수당관리목록 조회 등록*/
	public Map<String,Object> findSudangList();
	public void batchSudangProcess(Map<String,Object> sudangInfoList);
	
	/*기본 근무시간 설정 가져오기*/
	public List<BaseWorkTimeTO> findBaseWorkTimeList();
	
	/*기본 근무 시간 추가 수정 삭제*/
	public void addBaseWorkTime(BaseWorkTimeTO baseWorkTimeBean);
	public void editBaseWorkTime(BaseWorkTimeTO baseWorkTimeBean);
	public void removeBaseWorkTimeList(List<BaseWorkTimeTO> baseWorkTimeList);

	/*소득세율 4대보험 부담률 조회*/
	public List<DeductionTaxTO> findDeductionTaxList();
	public List<IncomeTaxTO> findIncomeTaxList();
	
	/*급여공제항목 삭제*/
	public void removeDeductionTax(DeductionTaxTO deductionsTaxBean);
	public void removeIncomeTaxList(List<IncomeTaxTO> incomeTaxList);
	
	/*금여공제 등록 */
	public void addDeductionTax(DeductionTaxTO deductionsTaxBean);
	public void addIncomeTaxList(List<IncomeTaxTO> incomeTaxList);

	
	
}