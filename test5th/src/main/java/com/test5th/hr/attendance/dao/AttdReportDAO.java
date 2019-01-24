package com.test5th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdReportBean;
import com.test5th.hr.attendance.to.MonthAttdReportBean;
import com.test5th.hr.attendance.to.OverNightReportBean;

public interface AttdReportDAO {
	
	public List<OverNightReportBean> selectOverNightReport(HashMap<String,String> map);
	
	public void updateRequestStatus(OverNightReportBean overNightReportBean);
	
	public List<DailyAttdReportBean> selectApprovalDailyAttdReport(String baseDay);
	
	public void updateDailyAttdCloseYN(DailyAttdReportBean attdReportBean);
	
	/*미마감 내역 생성 */
	public Map<String, Object> createDailyAttdReport(Map<String,String> map);
	/*생성된 일근태 미마감 내역을 호출함 바로 위에서 생성(프로시저)*/
	public List<DailyAttdReportBean> selectDailyAttdReportByDate(String baseDay);
	
	//연장 심야 승인
	public List<OverNightReportBean> selectOverNightReportByCondition(ConditionBean condition);
	
	public void updateApprovalStatus(OverNightReportBean overNightReportBean);
	
	public void insertOverNightReport(OverNightReportBean overNightReportBean);

	public void deleteOverNightReport(OverNightReportBean overNightReportBean);
	
	
	
	//월근태 미마감 항목조회
	public List<DailyAttdReportBean> selectUnClosedDailyAttdReport(String baseYearMonth);
	
	//월근태 계산 프로시저
	public Map<String, Object> createMonthAttdReport(Map<String,String> map);
	
	//계산한 항목 호출 
	public List<MonthAttdReportBean> selectDailyAttdReportByYearMonth(String baseYearMonth);
	
	// 월근태 마감 
	public void updateMonthAttdCloseYN(Map<String,String> map);
	
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportBean> selectClosedMonthAttdReport(String baseYearMonth);
	
}