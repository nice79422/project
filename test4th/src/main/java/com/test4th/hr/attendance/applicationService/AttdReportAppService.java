package com.test4th.hr.attendance.applicationService;

import java.util.List;

import com.test4th.common.exception.ProcedureException;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdReportBean;
import com.test4th.hr.attendance.to.MonthAttdReportBean;
import com.test4th.hr.attendance.to.OverNightReportBean;

public interface AttdReportAppService {
	//연장 심야 신청 리스트 
	public List<OverNightReportBean> findOverNightReport();
	//연장심야 일괄 신청
	public void updateRequestStatus(List<OverNightReportBean> overNightReportList);
	//해당 일자의 일근태 마감 내역 조회 
	public List<DailyAttdReportBean> findApprovalDailyAttdReport(String baseDay);
	//일 근태 마감 여부 수정 (N -> Y)(Y -> N)
	public void updateDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList);
	
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<DailyAttdReportBean> findDailyAttdReport(String baseDay) throws ProcedureException;
	
	//연장심야관리목록조히
	public List<OverNightReportBean> findOverNightReportByCondition(ConditionBean condition);
	
	//연장 심야 상세 저장
	public void batchOverNight(List<OverNightReportBean> overNightReportList);
	
	//월 근태 미마감 조회시 	
	public List<DailyAttdReportBean> findUnClosedDailyAttdReport(String baseYearMonth);
	
	//월근태에서 일근태 미마감 항목 일괄 승인 시
	public List<DailyAttdReportBean> batchDailyAttdCloseYN(List<DailyAttdReportBean> dailyAttdReportList);
	
	// 월근태 계산 
	public List<MonthAttdReportBean> findMonthAttdReport(String baseYearMonth) throws ProcedureException;
	
	// 월 근태 승인 마감
	public void updateMonthAttdCloseYN(List<MonthAttdReportBean> monthAttdReportList);
	
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportBean> findClosedMonthAttdReport(String baseYearMonth);
	
	
}
