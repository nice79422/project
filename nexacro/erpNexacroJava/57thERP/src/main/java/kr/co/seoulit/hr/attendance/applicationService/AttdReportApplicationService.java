package kr.co.seoulit.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

public interface AttdReportApplicationService {
	//연장 심야 신청 리스트 
	public List<OverNightReportTO> findOverNightReport(HashMap<String,String> map);
	//연장 심야 상세 저장
	public void batchOverNight(List<OverNightReportTO> overNightReportList);
	//월 근태 미마감 조회시 
	public List<DailyAttdReportTO> findUnClosedDailyAttdReport(HashMap<String, Object> map);
	// 월근태 계산 
	public List<MonthAttdReportTO> findMonthAttdReport(HashMap<String, Object> map) throws ProcedureException;
	//월근태에서 일근태 미마감 항목 일괄 승인 시
	public List<DailyAttdReportTO> batchDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList);
	//일 근태 마감 여부 수정 (N -> Y)(Y -> N)
	public void updateDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList);
	// 월근태 마감 내역 조회 
	public List<MonthAttdReportTO> findClosedMonthAttdReport(HashMap<String, Object> map);
	// 월 근태 승인 마감
	public void updateMonthAttdCloseYN(List<MonthAttdReportTO> monthAttdReportList);
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<DailyAttdReportTO> findDailyAttdReport(String basicDate) throws ProcedureException;
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드
	public List<DailyAttdReportTO> findApprovalDailyAttdReport(String basicDate);
	// 연장 심야 승인관리 
	public List<OverNightReportTO> findOverNightReportByCondition(HashMap<String, Object> map);
}
