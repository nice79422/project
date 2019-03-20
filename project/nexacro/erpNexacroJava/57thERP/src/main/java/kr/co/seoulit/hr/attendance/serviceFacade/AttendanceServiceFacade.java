package kr.co.seoulit.hr.attendance.serviceFacade;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;
import kr.co.seoulit.hr.attendance.to.DailyAttdTO;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

public interface AttendanceServiceFacade {

	//일근태 정보 가져 오기
	public List<DailyAttdTO> findDailyAttdList(HashMap<String,String> map);
	
	//일근태 등록,수정,삭제 
	public void batchDailyAttd(List<DailyAttdTO> dailyAttdList);	
	
	//해당 사원의 연차 신청 정보 얻어 오기 
	public List<DayAnnualTO> findAnnualMgt(HashMap<String,String> map);
	
	//근태외 신청 리스트 가져 오기
	public List<DailyAttdRestTO> findAttdRestList(HashMap<String,Object> map);
	
	//근태외 신청 등록,수정,삭제 
	public void batchDailyAttdRest(List<DailyAttdRestTO> dailyAttdRestList);

	// 일근태 승인관리 부분에서 조건에 따라 조회하기 위한 메서드 
	public List<DailyAttdTO> findDailyAttdListByCondition(HashMap<String,Object> map);
	
	// 근태외 승인관리 부분에서 조건에 따라 조회하기 위한 메서드
	public List<DailyAttdRestTO> findAttdRestListByCondition(HashMap<String,Object> map);
	
	// 연차 신청 
	public List<DayAnnualTO> addDayAnnual(DayAnnualTO dayAnnualTO);	
	
	//연장 심야 승인 저장
	public void batchOverNight(List<OverNightReportTO> overNightReportList);

	//연장심야 신청 찾기
	public List<OverNightReportTO> findOverNightReport(HashMap<String, String> map);
	
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<DailyAttdReportTO> findDailyAttdReport(String basicDate) throws ProcedureException;

	//월근태 미 마감 조회 시 
	public List<DailyAttdReportTO> findUnClosedDailyAttdReport(HashMap<String, Object> map);
	
	//월근태 계산 하는 부분 
	public List<MonthAttdReportTO> findMonthAttdReport(HashMap<String, Object> map) throws ProcedureException;
	
	//월근태에서 일근태 미마감 항목 일광 승인시 
	public List<DailyAttdReportTO> batchDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList);

	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	public List<DailyAttdReportTO> findApprovalDailyAttdReport(String basicDate);
	
	/*해당날짜의 일근태를 마감하는 메서드 (마감여부를 Y -> N으로 )(N -> Y)*/
	public void updateDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList);
	
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportTO> findClosedMonthAttdReport(HashMap<String,Object> map);
	//월근태 마감 승인 (N -> Y)
	public void updateMonthAttdCloseYN(List<MonthAttdReportTO> monthAttdReportList);
	// 연장 심야 승인관리 
	public List<OverNightReportTO> findOverNightReportByCondition(HashMap<String,Object> map);
	
	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드
	public List<DayAnnualTO> findDayAnnualListByCondition(HashMap<String,Object> map);
	
	//연차를 일괄적으로 승인처리 하는 메서드 
	public void batchDayAnnual(List<DayAnnualTO> dayAnnualList);
}
