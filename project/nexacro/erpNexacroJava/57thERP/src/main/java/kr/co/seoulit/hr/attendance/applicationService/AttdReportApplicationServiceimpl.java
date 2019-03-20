package kr.co.seoulit.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.attendance.dao.AttdReportDAO;
import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

@Component
public class AttdReportApplicationServiceimpl implements AttdReportApplicationService{
	/* AttdReportDAO setting */
	
	@Autowired
	private AttdReportDAO attdReportDAO;

	@Override
	//연장 심야 신청 리스트 
	public List<OverNightReportTO> findOverNightReport(HashMap<String, String> map) {
		// TODO Auto-generated method stub
		return attdReportDAO.selectOverNightReport(map);
	}
	
	//연장 심야 신청등록, 신청삭제, 승인업데이트
	public void batchOverNight(List<OverNightReportTO> overNightReportList){
		           		
		for(OverNightReportTO overNightReportTO:overNightReportList){
			
			switch(overNightReportTO.getStatus()) {
			
			case "inserted" : attdReportDAO.insertOverNightReport(overNightReportTO); break;
			case "updated" : attdReportDAO.updateApprovalStatus(overNightReportTO); break;
			case "deleted" : attdReportDAO.deleteOverNightReport(overNightReportTO); break;
		
			}
	    }
	}

	@Override
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	public List<DailyAttdReportTO> findUnClosedDailyAttdReport(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return attdReportDAO.selectUnClosedDailyAttdReport(map);
	}
	
	@Override
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<DailyAttdReportTO> findDailyAttdReport(String basicDate) throws ProcedureException{
		
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("basicDate", basicDate);
		
		attdReportDAO.createDailyAttdReport(map);
		
		 int errorCode=Integer.parseInt((String) map.get("errorCode"));
			
		 if(errorCode < 0){
	            throw new ProcedureException((String) map.get("errorMsg"));
		 }
		
		return attdReportDAO.selectDailyAttdReportByDate(basicDate);
	}
	
	@Override
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드
	public List<DailyAttdReportTO> findApprovalDailyAttdReport(String basicDate){

		return attdReportDAO.selectApprovalDailyAttdReport(basicDate);
	}

	@Override
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public List<MonthAttdReportTO> findMonthAttdReport(HashMap<String, Object> map) throws ProcedureException {
		// TODO Auto-generated method stub
		attdReportDAO.createMonthAttdReport(map);
		 int errorCode=Integer.parseInt((String) map.get("errorCode"));
		 //System.out.println("프로시저에서 에러발생 ::"+map.get("errorMsg"));

		 if(errorCode < 0){
	            throw new ProcedureException((String) map.get("errorMsg"));
		 }
		return attdReportDAO.selectDailyAttdReportByYearMonth(map);
	}
 
	@Override
	// 해당날짜의 일근태를 마감하는 메서드 (Y-> N / N-> Y ) 
	public void updateDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList) {
		 for(DailyAttdReportTO dailyAttdReportTO : dailyAttdReportList) {
			 attdReportDAO.updateDailyAttdCloseYN(dailyAttdReportTO);
	     }
	}
	
	@Override
	//월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드
	public List<DailyAttdReportTO> batchDailyAttdCloseYN(List<DailyAttdReportTO> dailyAttdReportList) {
		// TODO Auto-generated method stub
		String baseYearMonth = null;
		for(DailyAttdReportTO dailyAttdReportTO : dailyAttdReportList) {
			attdReportDAO.updateDailyAttdCloseYN(dailyAttdReportTO);
			if(baseYearMonth == null) {
				baseYearMonth = dailyAttdReportTO.getBasicDay(); //날짜를 얻어옴
			}
			
		}
		baseYearMonth = baseYearMonth.substring(0, 6);
		HashMap<String,Object> map=new HashMap<>();
		map.put("baseYearMonth", baseYearMonth);
		return findUnClosedDailyAttdReport(map);
	}

	@Override
	public List<MonthAttdReportTO> findClosedMonthAttdReport(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return attdReportDAO.selectClosedMonthAttdReport(map);
	}

	@Override
	public void updateMonthAttdCloseYN(List<MonthAttdReportTO> monthAttdReportList) {
		// TODO Auto-generated method stub
		String basicYearMonth = monthAttdReportList.get(0).getBasicYearMonth();
		String closeYn = monthAttdReportList.get(0).getCloseYn();
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("basicYearMonth", basicYearMonth);
		map.put("closeYn", closeYn);  // map에 담아서 넘김.. 
		attdReportDAO.updateMonthAttdCloseYN(map);
		
	}

	@Override
	// 연장 심야 승인관리 
	public List<OverNightReportTO> findOverNightReportByCondition(HashMap<String, Object> map) {
		
		return attdReportDAO.selectOverNightReportByCondition(map);
	}
}
