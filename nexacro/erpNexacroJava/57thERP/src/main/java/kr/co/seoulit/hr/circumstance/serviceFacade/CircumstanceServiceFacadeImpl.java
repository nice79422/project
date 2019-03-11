package kr.co.seoulit.hr.circumstance.serviceFacade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.applicationService.AnnualLeaveApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.BaseWorkTimeApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.DeductionInsurApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.HolidayApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.PayDeductionApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.PayStepApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.SalPaymentDateApplicationService;
import kr.co.seoulit.hr.circumstance.applicationService.SudangMgnApplicationService;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.hr.circumstance.to.BaseWorkTimeTO;
import kr.co.seoulit.hr.circumstance.to.DeductionTaxTO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;
import kr.co.seoulit.hr.circumstance.to.IncomeTaxTO;
import kr.co.seoulit.hr.circumstance.to.PayDeductionItemTO;
import kr.co.seoulit.hr.circumstance.to.PayStepTO;
import kr.co.seoulit.hr.circumstance.to.SalPaymentDateTO;
import kr.co.seoulit.hr.circumstance.to.SudangInfoTO;


@Service
public class CircumstanceServiceFacadeImpl implements CircumstanceServiceFacade{

	@Autowired
	private PayStepApplicationService payStepApplicationService;

	@Autowired
	private HolidayApplicationService holidayApplicationService;

	@Autowired
	private AnnualLeaveApplicationService annualLeaveApplicationService;

	@Autowired
	private SalPaymentDateApplicationService salPaymentDateApplicationService;

	@Autowired
	private PayDeductionApplicationService payDeductionApplicationService;

	@Autowired
	private SudangMgnApplicationService sudangMgnApplicationService;
	
	@Autowired
	private BaseWorkTimeApplicationService baseWorkTimeApplicationService;
	
	@Autowired
	private DeductionInsurApplicationService deductionsInsurApplicationService;	


	@Override
	public List<PayStepTO> findPayStepList() {
		// TODO Auto-generated method stub
		return payStepApplicationService.findPayStepList();
	}

	@Override
	public void batchPayStepList(List<PayStepTO> payStepList) {
		// TODO Auto-generated method stub
		payStepApplicationService.batchPayStepList(payStepList);
	}

	/* 휴일목록을 가지고 오는 메서드 */
	@Override
	public List<HolidayTO> findHolidayList(HashMap<String, Object> dateList) {
		return holidayApplicationService.findHolidayList(dateList);
	}

	/* 휴일관련 일괄처리를 하는 메서드 */
	@Override
	public void batchHoliday(List<HolidayTO> holidayList) {
		holidayApplicationService.batchHoliday(holidayList);
	}

	/* 전체 휴일목록을 가져오는 메서드 */
	@Override
	public List<HolidayTO> findHolidayListAll() {
		// TODO Auto-generated method stub
		return holidayApplicationService.findHolidayListAll();
	}

	/* 연차테이블을 조회하는 메서드 */
	@Override
	public List<AnnualLeaveTO> findAnnualLeaveList() {

		return annualLeaveApplicationService.findAnnualLeaveList();
	}

	/* 해당년도, 해당사원의 연차정보를 생성하는 메서드 */
	@Override
	public List<AnnualLeaveTO> createAnnualLeave(HashMap<String, Object> map) throws ProcedureException{
		return annualLeaveApplicationService.createAnnualLeave(map);
	}

	/* 연차와 관련된 일괄처리를 하는 메서드 */
	@Override
	public void batchAnnualLeave(List<AnnualLeaveTO> annualLeaveList) {
		annualLeaveApplicationService.batchAnnualLeave(annualLeaveList);
	}

	/* 연차를 신청할때 연차관리테이블을 수정하기 위한 메서드 */
	@Override
	public List<AnnualLeaveTO> editAnnualLeaveMgt(HashMap<String, Object> map){
		return annualLeaveApplicationService.editAnnualLeaveMgt(map);
	}

	/* */
	@Override
	public List<SalPaymentDateTO> findSalPaymentDateList(String inputedYearMonth){
		return salPaymentDateApplicationService.findSalPaymentDateList(inputedYearMonth);
	}

	/* */
	@Override
	public void batchSalPaymentDate(List<SalPaymentDateTO> salPaymentDateList){
		salPaymentDateApplicationService.batchSalPaymentDate(salPaymentDateList);
	}

	@Override
	public List<HolidayTO> addHoliday(HashMap<String, Object> map) throws ProcedureException {
		// TODO Auto-generated method stub
		return holidayApplicationService.addHoliday(map);
	}

	@Override
	public List<PayDeductionItemTO> findPayDeductionList() {
		// TODO Auto-generated method stub
		return payDeductionApplicationService.findPayDeductionList();
	}

	@Override
	public void batchPayDeduction(List<PayDeductionItemTO> payDeductionList) {
		// TODO Auto-generated method stub
		payDeductionApplicationService.batchPayDeduction(payDeductionList);
	}

	@Override
	public Map<String, Object> findSudangList() {
		// TODO Auto-generated method stub
		return sudangMgnApplicationService.findSudangList();
	}

	@Override
	public void batchSudangProcess(Map<String, Object> sudangInfoList) {
		// TODO Auto-generated method stub
		sudangMgnApplicationService.batchSudangProcess(sudangInfoList);
		
	
	}

	@Override
	public List<BaseWorkTimeTO> findBaseWorkTimeList() {
		return baseWorkTimeApplicationService.findBaseWorkTimeList();
	}

	@Override
	public void addBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO) {
		baseWorkTimeApplicationService.addBaseWorkTime(baseWorkTimeTO);
		
	}

	@Override
	public void editBaseWorkTime(BaseWorkTimeTO baseWorkTimeTO) {
		baseWorkTimeApplicationService.editBaseWorkTime(baseWorkTimeTO);
		
	}

	@Override
	public List<DeductionTaxTO> findDeductionTaxList() {
		return deductionsInsurApplicationService.findDeductionTaxList();
	}

	@Override
	public List<IncomeTaxTO> findIncomeTaxList() {
		return deductionsInsurApplicationService.findIncomeTaxList();
	}

	@Override
	public void removeDeductionTax(DeductionTaxTO deductionsTaxTO) {
		deductionsInsurApplicationService.removeDeductionTax(deductionsTaxTO);
		
	}

	@Override
	public void removeIncomeTaxList(List<IncomeTaxTO> incomeTaxList) {
		deductionsInsurApplicationService.removeIncomeTaxList(incomeTaxList);
		
	}

	@Override
	public void addDeductionTax(DeductionTaxTO deductionsTaxTO) {
		deductionsInsurApplicationService.addDeductionTax(deductionsTaxTO);
		
	}

	@Override
	public void addIncomeTaxList(List<IncomeTaxTO> incomeTaxList) {
		deductionsInsurApplicationService.addIncomeTaxList(incomeTaxList);
		
	}

	@Override
	public void removeBaseWorkTimeList(List<BaseWorkTimeTO> baseWorkTimeList) {
		baseWorkTimeApplicationService.removeBaseWorkTimeList(baseWorkTimeList);
		
	}




}
