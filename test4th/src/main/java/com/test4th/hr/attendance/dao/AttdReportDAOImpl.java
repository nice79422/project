package com.test4th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdReportBean;
import com.test4th.hr.attendance.to.MonthAttdReportBean;
import com.test4th.hr.attendance.to.OverNightReportBean;

public class AttdReportDAOImpl extends IBatisDAO implements AttdReportDAO {
	
	// 연장 심야 근무 신청 목록 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<OverNightReportBean> selectOverNightReport(){
		return getSqlMapClientTemplate().queryForList("attdReport.selectOverNightReport");
	}
	
	//연장 심야 일괄 신청(N -> Y)
	@SuppressWarnings("deprecation")
	@Override
	public void updateRequestStatus(OverNightReportBean overNightReportBean){
		getSqlMapClientTemplate().update("attdReport.updateRequestStatus",overNightReportBean);
	}
	
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdReportBean> selectApprovalDailyAttdReport(String baseDay) {
		return getSqlMapClientTemplate().queryForList("attdReport.selectApprovalDailyAttdReport", baseDay);
	}
	
	
	// 해당하는 날짜의 일/월근태를 마감하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateDailyAttdCloseYN(DailyAttdReportBean attdReportBean) {
		getSqlMapClientTemplate().update("attdReport.updateDailyAttdCloseYN",attdReportBean);
	}
	
	// 일근태 관리테이블에 해당 날짜의 데이터가 미마감이면 해당날짜의 일근태관리테이블에 들어갈 데이터를 생성 
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> createDailyAttdReport(String baseDay){
		Map<String, Object> map = new HashMap<>();
	       map.put("baseDay", baseDay);
	       getSqlMapClientTemplate().queryForObject("attdReport.createDailyAttdReport",map);
		return map;
	}
	
	
	// 해당날짜의 일근태관리테이블을 조회하는 메서드 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdReportBean> selectDailyAttdReportByDate(String baseDay) {
		return getSqlMapClientTemplate().queryForList("attdReport.selectDailyAttdReportByDate", baseDay);
	}
	
	//연장 심야 승인 관리 목록 조회
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<OverNightReportBean> selectOverNightReportByCondition(ConditionBean condition){
		return getSqlMapClientTemplate().queryForList("attdReport.selectOverNightReportByCondition",condition);
	}
	
	//연장 심야 승인 등록
	@SuppressWarnings("deprecation")
	@Override
	public void updateApprovalStatus(OverNightReportBean overNightReportBean){
		getSqlMapClientTemplate().update("attdReport.updateApprovalStatus",overNightReportBean);
	}
	
	
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdReportBean> selectUnClosedDailyAttdReport(String baseYearMonth) {
		return getSqlMapClientTemplate().queryForList("attdReport.selectUnClosedDailyAttdReport",baseYearMonth);
	}
	
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public Map<String, Object> createMonthAttdReport(String baseYearMonth) {
		Map<String, Object> map = new HashMap<>();
	       map.put("baseYearMonth", baseYearMonth);
	       getSqlMapClientTemplate().queryForObject("attdReport.createMonthAttdReport",map);
		return map;
	}
	
	//월근태 항목 호출 !
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<MonthAttdReportBean> selectDailyAttdReportByYearMonth(String baseYearMonth) {
		return getSqlMapClientTemplate().queryForList("attdReport.selectDailyAttdReportByYearMonth", baseYearMonth);
	}
	
	
	// 월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드  (월근태 미승인에서 승인으로 변성)
	@SuppressWarnings("deprecation")
	@Override
	public void updateMonthAttdCloseYN(Map<String,String> map) {
		getSqlMapClientTemplate().update("attdReport.updateMonthAttdCloseYN",map);
	}

	
	//월근태 마감 내역 조회 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<MonthAttdReportBean> selectClosedMonthAttdReport(String baseYearMonth) {
		return getSqlMapClientTemplate().queryForList("attdReport.selectClosedMonthAttdReport", baseYearMonth);
	}

}
