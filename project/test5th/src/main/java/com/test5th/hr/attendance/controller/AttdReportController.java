package com.test5th.hr.attendance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.attendance.serviceFacade.AttendanceServiceFacade;
import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdReportBean;
import com.test5th.hr.attendance.to.MonthAttdReportBean;
import com.test5th.hr.attendance.to.OverNightReportBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class AttdReportController{
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
		List<OverNightReportBean> overNightReportList=attendanceServiceFacade.findOverNightReport(map);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportBean.class);
    }

	/*연장 심야 일괄 신청버튼 --> 없앴다*/
	@RequestMapping("hr/attendance/updateRequestStatus.do")
	public void updateRequestStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<OverNightReportBean> overNightReportList=datasetBeanMapper.datasetToBeans(inData, OverNightReportBean.class);
		attendanceServiceFacade.updateRequestStatus(overNightReportList);
    }
	
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	@RequestMapping("hr/attendance/findApprovalDailyAttdReport.do")
	public void findApprovalDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseDay = inData.getVariable("baseDay").getString();
		List<DailyAttdReportBean> dailyAttdReportList=attendanceServiceFacade.findApprovalDailyAttdReport(baseDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportBean.class);
	}
	
	// 해당날짜의 일근태를 마감하는 메서드 (마감여부를 Y -> N으로 ) (N-> Y)
	@RequestMapping("hr/attendance/updateDailyAttdCloseYN.do")
	public void updateDailyAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DailyAttdReportBean> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportBean.class);
		attendanceServiceFacade.updateDailyAttdCloseYN(dailyAttdReportList);
	}
	
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	@RequestMapping("hr/attendance/findDailyAttdReport.do")
	public void findDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseDay = inData.getVariable("baseDay").getString();
		List<DailyAttdReportBean> dailyAttdReportList=attendanceServiceFacade.findDailyAttdReport(baseDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportBean.class);
	}

	// 연장 심야 승인관리 
	@RequestMapping("hr/attendance/findOverNightReportByCondition.do")
	public void findOverNightReportByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		//System.out.println(inData);
		String deptCode = inData.getVariable("deptCode").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		//System.out.println(deptCode);
		//System.out.println(basicDay);
		//System.out.println(approvalStatus);
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<OverNightReportBean> overNightReportList=attendanceServiceFacade.findOverNightReportByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportBean.class);
	}
	
	
	/*연장심야 승인 결과 저장*/
	@RequestMapping("hr/attendance/batchOverNight.do")
	public void batchOverNight(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		List<OverNightReportBean> overNightReportList=datasetBeanMapper.datasetToBeans(inData, OverNightReportBean.class);
		

		attendanceServiceFacade.batchOverNight(overNightReportList);
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportBean.class);
    }
	
	
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	@RequestMapping("hr/attendance/findUnClosedDailyAttdReport.do")
	public void findUnClosedDailyAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		List<DailyAttdReportBean> UnclosedDailyAttdReportList=attendanceServiceFacade.findUnClosedDailyAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportBean.class);
	}
	
	// 월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/attendance/batchDailyAttdCloseYN.do")
	public void batchDailyAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		List<DailyAttdReportBean> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportBean.class);
		List<DailyAttdReportBean> UnclosedDailyAttdReportList=attendanceServiceFacade.batchDailyAttdCloseYN(dailyAttdReportList);
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportBean.class);
	}
	
	
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	@RequestMapping("hr/attendance/findMonthAttdReport.do")
	public void findMonthAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		List<MonthAttdReportBean> monthAttdReportList=attendanceServiceFacade.findMonthAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportBean.class);
	}
	
	// 기준년월의 데이터를 마감처리하는 메서드 
	@RequestMapping("hr/attendance/updateMonthAttdCloseYN.do")
	public void updateMonthAttdCloseYN(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<MonthAttdReportBean> monthAttdReportList=datasetBeanMapper.datasetToBeans(inData, MonthAttdReportBean.class);
		attendanceServiceFacade.updateMonthAttdCloseYN(monthAttdReportList);
	}

	//월근태 미간 매역 조회 
	@RequestMapping("hr/attendance/findClosedMonthAttdReport.do")
	public void findClosedMonthAttdReport(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String baseYearMonth = inData.getVariable("baseYearMonth").getString();
		List<MonthAttdReportBean> monthAttdReportList=attendanceServiceFacade.findClosedMonthAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportBean.class);
    }



}