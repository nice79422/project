package kr.co.seoulit.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

public interface AttdReportDAO {

	List<OverNightReportTO> selectOverNightReport(HashMap<String, String> map);
	
	//연장 심야 승인
	public void insertOverNightReport(OverNightReportTO overNightReportTO);
	
	public void updateApprovalStatus(OverNightReportTO overNightReportTO);
	
	public void deleteOverNightReport(OverNightReportTO overNightReportTO);
	
	public void createDailyAttdReport(HashMap<String, String> map);
	
	public List<DailyAttdReportTO> selectDailyAttdReportByDate(String basicDate);
	
	public List<DailyAttdReportTO> selectApprovalDailyAttdReport(String basicDate);
	//월근태 미마감 항목조회
	List<DailyAttdReportTO> selectUnClosedDailyAttdReport(HashMap<String, Object> map);
	//월근태 계산 프로시저
	public Map<String, Object> createMonthAttdReport(HashMap<String, Object> map);	
	//계산한 항목 호출 
	public List<MonthAttdReportTO> selectDailyAttdReportByYearMonth(HashMap<String, Object> map);
	//일근태 승인 N->Y 아람언니가 만들엇을듯
	public void updateDailyAttdCloseYN(DailyAttdReportTO attdReportTO);
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportTO> selectClosedMonthAttdReport(HashMap<String, Object> map);

	void updateMonthAttdCloseYN(HashMap<String, String> map);
	// 연장 심야 승인관리 
	public List<OverNightReportTO> selectOverNightReportByCondition(HashMap<String, Object> map);
}
