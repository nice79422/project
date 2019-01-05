package com.test4th.hr.attendance.service;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.attendance.applicationService.AttdReportAppService;
import com.test4th.hr.attendance.applicationService.DailyAttdAppService;
import com.test4th.hr.attendance.applicationService.DailyAttdRestAppService;
import com.test4th.hr.attendance.applicationService.DayAnnualAppService;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdBean;
import com.test4th.hr.attendance.to.DailyAttdReportBean;
import com.test4th.hr.attendance.to.DailyAttdRestBean;
import com.test4th.hr.attendance.to.DayAnnualBean;
import com.test4th.hr.attendance.to.MonthAttdReportBean;
import com.test4th.hr.attendance.to.OverNightReportBean;

public class AttendanceServiceFacadeImpl implements AttendanceServiceFacade{
	private DailyAttdAppService dailyAttdAppService;
	
	private DayAnnualAppService dayAnnualAppService;
	
	private DailyAttdRestAppService dailyAttdRestAppService;

	
	private AttdReportAppService attdReportAppService;
	
	
	public void setDailyAttdAppService(DailyAttdAppService dailyAttdAppService) {
		this.dailyAttdAppService = dailyAttdAppService;
	}
	
	public void setDayAnnualAppService(DayAnnualAppService dayAnnualAppService) {
		this.dayAnnualAppService = dayAnnualAppService;
	}
	
	public void setDailyAttdRestAppService(DailyAttdRestAppService dailyAttdRestAppService) {
		this.dailyAttdRestAppService = dailyAttdRestAppService;
	}


	public void setAttdReportAppService(AttdReportAppService attdReportAppService) {
		this.attdReportAppService = attdReportAppService;
	}

	

	@Override
	/* 근태목록을 가지고오는 메서드 */
	public List<DailyAttdBean> findDailyAttdList(String empCode,String fromDate,String toDate) {
		return dailyAttdAppService.findDailyAttdList(empCode,fromDate,toDate);
	}

	@Override
	// 근태신청한 내용을 추가하는 메서드
	public List<DailyAttdBean> addDailyAttd(DailyAttdBean dailyAttdBean) {
		return dailyAttdAppService.addDailyAttd(dailyAttdBean);
	}
	
	// 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 
	@Override
	public List<DayAnnualBean> findAnnualMgt(String empCode,String standardDate) {
		return dayAnnualAppService.findAnnualMgt(empCode,standardDate);
	}
	
	@Override
	// 근태외목록을 가지고 오는 메서드 
	public List<DailyAttdRestBean> findAttdRestList(String empCode) {
		return dailyAttdRestAppService.findAttdRestList(empCode);
	}
	
	// 연차를 신청하는 메서드 
	@Override
	public List<DayAnnualBean> addDayAnnual(DayAnnualBean dayAnnualBean) {
		return dayAnnualAppService.addDayAnnual(dayAnnualBean);
	}
		
	

	@Override
	// 근태외 신청한 내용을 추가하는 메서드 
	public List<DailyAttdRestBean> addDailyAttdRest(DailyAttdRestBean dailyAttdRestBean) {
		return dailyAttdRestAppService.addDailyAttdRest(dailyAttdRestBean);
	}
	
	@Override
	// 연장 심야 신청 리스트
	public List<OverNightReportBean> findOverNightReport(String empCode,String fromDate,String toDate){
		return attdReportAppService.findOverNightReport(empCode,fromDate,toDate);
	} 
	/*연장심야 일괄 신청*/
	public void updateRequestStatus(List<OverNightReportBean> overNightReportList){
		attdReportAppService.updateRequestStatus(overNightReportList);
	}
	
	
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	@Override
	public List<DailyAttdReportBean> findApprovalDailyAttdReport(String baseDay){
		return attdReportAppService.findApprovalDailyAttdReport(baseDay);
	}
	
	
	
	// 해당날짜의 일근태를 마감(수정 )하는 메서드 (N -> Y)(Y -> N)
	@Override
	public void updateDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList) {
		attdReportAppService.updateDailyAttdCloseYN(dailyAttdReportList);
	}
	
	
	// 일근태 승인관리 부분에서 일근태를 조건에 따라 조회하기 위한 메서드 
	@Override
	public List<DailyAttdBean> findDailyAttdListByCondition(ConditionBean conditionBean) {
		return dailyAttdAppService.findDailyAttdListByCondition(conditionBean);
	}
	
	
	// 근태외 승인관리 부분에서 조건에 따라 조회하기 위한 메서드 
	@Override
	public List<DailyAttdRestBean> findAttdRestListByCondition(ConditionBean conditionBean) {
		return dailyAttdRestAppService.findAttdRestListByCondition(conditionBean);
	}
	
	
	//일근태 미승인을 승인으로 변경시 (N이 없어짐 승은으로 변경 되었으면 값이 안나옴?)
	@Override
	public List<DailyAttdBean> findUnApprovedDailyAttdList(String basicDay) {
		return dailyAttdAppService.findUnApprovedDailyAttdList(basicDay);
	}
	
	// 일근태를 일괄적으로 처리하는 메서드 (N -> Y 변경 하는 부분 )
	@Override
	public void batchDailyAttd(List<DailyAttdBean> dailyAttdList) {
		dailyAttdAppService.batchDailyAttd(dailyAttdList);
	}

	/*일근태후 미승인 -> 승인 후 목록 던짐 */
	@Override
	public List<DailyAttdRestBean> findUnApprovedDailyAttdRestList(String basicDay) {
		return dailyAttdRestAppService.findUnApprovedDailyAttdRestList(basicDay);
	}
	
	
	// 근태외 승인관리 부분을 일괄적으로 처리하는 메서드 
	@Override
	public void batchDailyAttdRest(List<DailyAttdRestBean> dailyAttdRestList) {
		dailyAttdRestAppService.batchDailyAttdRest(dailyAttdRestList);
	}
	
	
	
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	@Override
	public List<DailyAttdReportBean> findDailyAttdReport(String baseDay) throws ProcedureException{
		return attdReportAppService.findDailyAttdReport(baseDay);
	}
		
	//연장 심야 승인관리 목록 조회
	public List<OverNightReportBean> findOverNightReportByCondition(ConditionBean condition){
		return attdReportAppService.findOverNightReportByCondition(condition);
	}
	
	
	//연장 심야 승인 결과 저장
	public void batchOverNight(List<OverNightReportBean> overNightReportList){
		attdReportAppService.batchOverNight(overNightReportList);
	}
	
	
	// 연차 승인관리 부분에서 연차를 조건에 따라 조회하기 위한 메서드 
	@Override
	public List<DayAnnualBean> findDayAnnualListByCondition(ConditionBean conditionBean) {
		return dayAnnualAppService.findDayAnnualListByCondition(conditionBean);
	}
	
	// 연차를 일괄적으로 처리하는 메서드 
	@Override
	public void batchDayAnnual(List<DayAnnualBean> dayAnnualList) {
		dayAnnualAppService.batchDayAnnual(dayAnnualList);
	}
	
	
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	@Override
	public List<DailyAttdReportBean> findUnClosedDailyAttdReport(String baseYearMonth) {
		return attdReportAppService.findUnClosedDailyAttdReport(baseYearMonth);
	}
	
	// 월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드 
	@Override
	public List<DailyAttdReportBean> batchDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList) {
		return attdReportAppService.batchDailyAttdCloseYN(dailyAttdReportList);
	}
	
	
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	@Override
	public List<MonthAttdReportBean> findMonthAttdReport(String baseYearMonth) throws ProcedureException {
		return attdReportAppService.findMonthAttdReport(baseYearMonth);
	}
	
	// 월근태 마감 기준년월의 데이터를 마감하는 메서드 
	@Override
	public void updateMonthAttdCloseYN(List<MonthAttdReportBean> monthAttdReportList) {
		attdReportAppService.updateMonthAttdCloseYN(monthAttdReportList);
	}
	
	// 월근태 마감 내역 조회 
	@Override
	public List<MonthAttdReportBean> findClosedMonthAttdReport(String baseYearMonth){

		return attdReportAppService.findClosedMonthAttdReport(baseYearMonth);
	}





}
