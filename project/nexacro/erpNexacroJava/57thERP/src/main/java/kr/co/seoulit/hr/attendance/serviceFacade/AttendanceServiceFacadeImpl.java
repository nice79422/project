package kr.co.seoulit.hr.attendance.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.hr.attendance.applicationService.DailyAttdRestApplicationService;
import kr.co.seoulit.hr.attendance.applicationService.DailyAttdAppService;
import kr.co.seoulit.hr.attendance.applicationService.DayAnnualApplicationService;
import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;
import kr.co.seoulit.hr.attendance.to.DailyAttdTO;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.attendance.applicationService.AttdReportApplicationService;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

@Service
public class AttendanceServiceFacadeImpl implements AttendanceServiceFacade{

	@Autowired
	private DayAnnualApplicationService dayAnnualApplicationService;
	@Autowired
	private DailyAttdRestApplicationService dailyAttdRestApplicationService;
	@Autowired
	private DailyAttdAppService dailyAttdAppService;
	@Autowired
	private AttdReportApplicationService attdReportApplicationService;
	

	// 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드
	@Override
	public List<DayAnnualTO> findAnnualMgt(HashMap<String,String> map) {
		return dayAnnualApplicationService.findAnnualMgt(map);
	}

	// 일근태 승인관리 부분에서 조건에 따라 조회하기 위한 메서드 
	@Override
	public List<DailyAttdTO> findDailyAttdListByCondition(HashMap<String,Object> map) {
		
		return dailyAttdAppService.findDailyAttdListByCondition(map);
	}

	// 근태외 승인관리 부분에서 조건에 따라 조회하기 위한 메서드 
	@Override
	public List<DailyAttdRestTO> findAttdRestListByCondition(HashMap<String, Object> map) {

		return dailyAttdRestApplicationService.findAttdRestListByCondition(map);
	}	

	@Override
	// 근태외 목록을 가지고 오는 메서드 
	public List<DailyAttdRestTO> findAttdRestList(HashMap<String,Object> map) {
		// TODO Auto-generated method stub
		return dailyAttdRestApplicationService.findAttdRestList(map);
	}
	
	@Override
	//근태외 목록을 등록,수정,삭제 메서드
	public void batchDailyAttdRest(List<DailyAttdRestTO> dailyAttdRestList) {

		dailyAttdRestApplicationService.batchDailyAttdRest(dailyAttdRestList);
	}
	
	@Override
	// 연차를 신청하는 메서드 
	public List<DayAnnualTO> addDayAnnual(DayAnnualTO dayAnnualTO) {
		// TODO Auto-generated method stub
		return dayAnnualApplicationService.addDayAnnual(dayAnnualTO);
	}
	
	@Override
	//근태 목록을 가지고오는 메서드
	public List<DailyAttdTO> findDailyAttdList(HashMap<String,String> map) {

		return dailyAttdAppService.findDailyAttdList(map);
	}
	
	@Override
	//근태 목록을 등록,수정,삭제 메서드
	public void batchDailyAttd(List<DailyAttdTO> dailyAttdList) {
		
		dailyAttdAppService.batchDailyAttd(dailyAttdList);
	}
	
	@Override
	// 연장 심야 신청 리스트
	public List<OverNightReportTO> findOverNightReport(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return attdReportApplicationService.findOverNightReport(map);
	}

	@Override
	//연장 심야 승인 결과 저장
	public void batchOverNight(List<OverNightReportTO> overNightReportList){
		
		attdReportApplicationService.batchOverNight(overNightReportList);
	}

	@Override
	//일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<DailyAttdReportTO> findDailyAttdReport(String basicDate) throws ProcedureException{

		return attdReportApplicationService.findDailyAttdReport(basicDate);
	}
	
	@Override
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	public List<DailyAttdReportTO> findApprovalDailyAttdReport(String basicDate) {

		return attdReportApplicationService.findApprovalDailyAttdReport(basicDate);
	}

	@Override
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	public List<DailyAttdReportTO> findUnClosedDailyAttdReport(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return attdReportApplicationService.findUnClosedDailyAttdReport(map);
	}


	@Override
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<MonthAttdReportTO> findMonthAttdReport(HashMap<String, Object> map) throws ProcedureException {
		// TODO Auto-generated method stub
		return attdReportApplicationService.findMonthAttdReport(map);
	}


	@Override
	public List<DailyAttdReportTO> batchDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList) {
		// TODO Auto-generated method stub
		return attdReportApplicationService.batchDailyAttdCloseYN(dailyAttdReportList);
	}


	@Override
	public void updateDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList) {
		// TODO Auto-generated method stub
		attdReportApplicationService.updateDailyAttdCloseYN(dailyAttdReportList);
	}


	@Override
	public List<MonthAttdReportTO> findClosedMonthAttdReport(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return attdReportApplicationService.findClosedMonthAttdReport(map);
	}


	@Override
	public void updateMonthAttdCloseYN(List<MonthAttdReportTO> monthAttdReportList) {
		// TODO Auto-generated method stub
		attdReportApplicationService.updateMonthAttdCloseYN(monthAttdReportList);
	}
	
	@Override
	// 연장 심야 승인관리 
	public List<OverNightReportTO> findOverNightReportByCondition(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return attdReportApplicationService.findOverNightReportByCondition(map);
	}
	
	@Override
	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드
	public List<DayAnnualTO> findDayAnnualListByCondition(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dayAnnualApplicationService.findDayAnnualListByCondition(map);
	}

	@Override
	//연차를 일괄적으로 승인처리 하는 메서드 
	public void batchDayAnnual(List<DayAnnualTO> dayAnnualList) {
		
		dayAnnualApplicationService.batchDayAnnual(dayAnnualList);
	}

}
