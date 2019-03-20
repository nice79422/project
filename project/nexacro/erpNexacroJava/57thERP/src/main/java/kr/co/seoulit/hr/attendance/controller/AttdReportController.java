package kr.co.seoulit.hr.attendance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.attendance.serviceFacade.AttendanceServiceFacade;
import kr.co.seoulit.hr.attendance.to.DailyAttdReportTO;
import kr.co.seoulit.hr.attendance.to.MonthAttdReportTO;
import kr.co.seoulit.hr.attendance.to.OverNightReportTO;

@Controller
public class AttdReportController {
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
	/*연장 심야 신청 조회버튼  */
	@RequestMapping("hr/attendance/findOverNightAttdReport.do")
	public void findOverNightAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
	
		String empCode = inData.getVariable("empCode").getString();
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();

		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", empCode);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		List<OverNightReportTO> overNightReportList=attendanceServiceFacade.findOverNightReport(map);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportTO.class);
    }
	
	/*연장심야 승인 결과 저장*/
	@RequestMapping("hr/attendance/batchOverNight.do")
	public void batchOverNight(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		List<OverNightReportTO> overNightReportList=datasetBeanMapper.datasetToBeans(inData, OverNightReportTO.class);
		
		for(OverNightReportTO oo:overNightReportList) {
		System.out.println("ddd:"+oo.getEmpCode());
		}
		attendanceServiceFacade.batchOverNight(overNightReportList);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportTO.class);
    }

	// 일근태관리테이블에서 기준일에 해당하는 데이터를  생성,조회하는 메서드 
	@RequestMapping("hr/attendance/findDailyAttdReport.do")
	public void findDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String basicDate = inData.getVariable("basicDate").getString();
		
		List<DailyAttdReportTO> dailyAttdReportList=attendanceServiceFacade.findDailyAttdReport(basicDate);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportTO.class);
	}
	
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	@RequestMapping("hr/attendance/findApprovalDailyAttdReport.do")
	public void findApprovalDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String baseDay = inData.getVariable("baseDay").getString();
		
		List<DailyAttdReportTO> dailyAttdReportList=attendanceServiceFacade.findApprovalDailyAttdReport(baseDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportTO.class);
	}
	
	// 해당날짜의 일근태를 마감하는 메서드 (마감여부를  N-> Y)
	@RequestMapping("hr/attendance/updateDailyAttdCloseYN.do")
	public void updateDailyAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DailyAttdReportTO> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportTO.class);
		attendanceServiceFacade.updateDailyAttdCloseYN(dailyAttdReportList);
	}
	
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	@RequestMapping("hr/attendance/findUnClosedDailyAttdReport.do")
	public void findUnClosedDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		HashMap<String,Object> map=new HashMap<>();
		map.put("baseYearMonth", baseYearMonth);
		List<DailyAttdReportTO> UnclosedDailyAttdReportList=attendanceServiceFacade.findUnClosedDailyAttdReport(map);
		for(DailyAttdReportTO oo:UnclosedDailyAttdReportList) {
		System.out.println("ddd:"+oo.getEmpCode());
		}
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportTO.class);
	}

	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	@RequestMapping("hr/attendance/findMonthAttdReport.do")
	public void findMonthAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		HashMap<String,Object> map=new HashMap<>();
		map.put("baseYearMonth", baseYearMonth);
		List<MonthAttdReportTO> monthAttdReportList=attendanceServiceFacade.findMonthAttdReport(map);
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportTO.class);
	}
	
	// 월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/attendance/batchDailyAttdCloseYN.do")
	public void batchDailyAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		List<DailyAttdReportTO> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportTO.class);
		List<DailyAttdReportTO> UnclosedDailyAttdReportList=attendanceServiceFacade.batchDailyAttdCloseYN(dailyAttdReportList);
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportTO.class);
	}
	
	// 월근태 마감 항목 조회 
	@RequestMapping("hr/attendance/findClosedMonthAttdReport.do")
	public void findClosedMonthAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		HashMap<String,Object> map=new HashMap<>();
		map.put("baseYearMonth", baseYearMonth);
		List<MonthAttdReportTO> monthAttdReportList=attendanceServiceFacade.findClosedMonthAttdReport(map);
		for(MonthAttdReportTO oo:monthAttdReportList) {
		System.out.println("dddaaaaaaaa:"+oo.getEmpCode());
		}
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportTO.class);
    }
	
	// 기준년월의 데이터를 마감처리하는 메서드 
	@RequestMapping("hr/attendance/updateMonthAttdCloseYN.do")
	public void updateMonthAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<MonthAttdReportTO> monthAttdReportList=datasetBeanMapper.datasetToBeans(inData, MonthAttdReportTO.class);
		attendanceServiceFacade.updateMonthAttdCloseYN(monthAttdReportList);
	}
	
	// 연장 심야 승인관리 
	@RequestMapping("hr/attendance/findOverNightReportByCondition.do")
	public void findOverNightReportByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	

		String deptCode = inData.getVariable("deptCode").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String approvalStatus = inData.getVariable("approvalStatus").getString();

		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("deptCode", deptCode);
		map.put("basicDay", basicDay);
		map.put("approvalStatus", approvalStatus);
		
		List<OverNightReportTO> overNightReportList=attendanceServiceFacade.findOverNightReportByCondition(map);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportTO.class);
	}
}
