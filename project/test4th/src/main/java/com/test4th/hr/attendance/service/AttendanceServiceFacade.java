package com.test4th.hr.attendance.service;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdBean;
import com.test4th.hr.attendance.to.DailyAttdReportBean;
import com.test4th.hr.attendance.to.DailyAttdRestBean;
import com.test4th.hr.attendance.to.DayAnnualBean;
import com.test4th.hr.attendance.to.MonthAttdReportBean;
import com.test4th.hr.attendance.to.OverNightReportBean;

public interface AttendanceServiceFacade {
	//근태 정보 가져 오기 
	public List<DailyAttdBean> findDailyAttdList(String empCode,String fromDate,String toDate);
	
	//근태 신청
	public List<DailyAttdBean> addDailyAttd(DailyAttdBean dailyAttdBean);
	
	//해당 사원의 연차 신청 정보 얻어 오기 
	public List<DayAnnualBean> findAnnualMgt(String empCode, String standardDate);
	
	//근태외 신청 리스트 가져 오기
	public List<DailyAttdRestBean> findAttdRestList(String empCode);
	
	// 연차 신청 
	public List<DayAnnualBean> addDayAnnual(DayAnnualBean dayAnnualBean);
	
	/*근태외 신청*/
	public List<DailyAttdRestBean> addDailyAttdRest(DailyAttdRestBean dailyAttdRestBean);
	
	/*연장심야 신청 찾기*/
	public List<OverNightReportBean> findOverNightReport(String empCode,String fromDate,String toDate);
	/*연장 심야 일괄 신청*/
	public void updateRequestStatus(List<OverNightReportBean> overNightReportList);
	
	/*일자별로 일근태 마감 조회 */
	public List<DailyAttdReportBean> findApprovalDailyAttdReport(String baseDay);
	/*해당날짜의 일근태를 마감하는 메서드 (마감여부를 Y -> N으로 )(N -> Y)*/
	public void updateDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList);
	
	/*미승인 일 근태 내역 조회 */
	public List<DailyAttdBean> findDailyAttdListByCondition(ConditionBean conditionBean);
	
	/*미승인 일근태외 내역 조회*/
	public List<DailyAttdRestBean> findAttdRestListByCondition(ConditionBean conditionBean);

	/*일근태 미승인을 승인으로변동 시 (N이 없어짐 승은으로 변경 되었으면 값이 안나옴?)*/
	public List<DailyAttdBean> findUnApprovedDailyAttdList(String basicDay);
	/*일근태 미승인을 승인으로 변경 하는 곳 결과는 바로 위에서 가져 간다. */
	public void batchDailyAttd(List<DailyAttdBean> dailyAttdList);
	
	/*일근태외 미승인 -> 승인 후 목록 던짐*/
	public List<DailyAttdRestBean> findUnApprovedDailyAttdRestList(String basicDay);

	/*일근태외 미승인 -> 승인 변경 */
	public void batchDailyAttdRest(List<DailyAttdRestBean> dailyAttdRestList);
	
	/* 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 */
	public List<DailyAttdReportBean> findDailyAttdReport(String baseDay) throws ProcedureException;
	
	/*연장 심야 승인 관리 목록조회*/
	public List<OverNightReportBean> findOverNightReportByCondition(ConditionBean condition);
	
	/*연장 심야 승인 저장*/
	public void batchOverNight(List<OverNightReportBean> overNightReportList);
	
	//연차 승인  
	public List<DayAnnualBean> findDayAnnualListByCondition(ConditionBean condition);
	public void batchDayAnnual(List<DayAnnualBean> dayAnnualList);

	
	//월근태 미 마감 조회 시 
	public List<DailyAttdReportBean> findUnClosedDailyAttdReport(String baseYearMonth);
	//월근태에서 일근태 미마감 항목 일광 승인시 
	public List<DailyAttdReportBean> batchDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList);
	
	//월근태 계산 하는 부분 
	public List<MonthAttdReportBean> findMonthAttdReport(String baseYearMonth) throws ProcedureException;
	
	//월근태 마감 승인 (N -> Y)
	public void updateMonthAttdCloseYN(List<MonthAttdReportBean> monthAttdReportList);
	
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportBean> findClosedMonthAttdReport(String baseYearMonth);
	

}
