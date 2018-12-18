package com.test4th.hr.attendance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.attendance.service.AttendanceServiceFacade;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdReportBean;
import com.test4th.hr.attendance.to.MonthAttdReportBean;
import com.test4th.hr.attendance.to.OverNightReportBean;
import com.tobesoft.platform.data.PlatformData;

public class AttdReportController extends AbstractMiplatformMultiActionController{
	/* AttendanceServiceFacade setting */
	private AttendanceServiceFacade attendanceServiceFacade;
	public void setAttendanceServiceFacade(AttendanceServiceFacade attendanceServiceFacade) {
		this.attendanceServiceFacade = attendanceServiceFacade;
	}

	
	/*연장 신야 신청 */
	public void findOverNightAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		List<OverNightReportBean> overNightReportList=attendanceServiceFacade.findOverNightReport();
		datasetBeanMapper.beansToDataset(outData, overNightReportList, OverNightReportBean.class);
    }

	/*연장/심야 일괄 신청*/
	public void updateRequestStatus(PlatformData inData, PlatformData outData) throws Exception {
		List<OverNightReportBean> overNightReportList=datasetBeanMapper.datasetToBeans(inData, OverNightReportBean.class);
		attendanceServiceFacade.updateRequestStatus(overNightReportList);
    }
	
	// 일근태관리테이블에서 기준일에 해당하는 승인된 데이터를 조회하는 메서드 
	public void findApprovalDailyAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		String baseDay = inData.getVariable("baseDay").getValue().asString();
		List<DailyAttdReportBean> dailyAttdReportList=attendanceServiceFacade.findApprovalDailyAttdReport(baseDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportBean.class);
	}
	
	// 해당날짜의 일근태를 마감하는 메서드 (마감여부를 Y -> N으로 ) (N-> Y)
	public void updateDailyAttdCloseYN(PlatformData inData, PlatformData outData) throws Exception {
		List<DailyAttdReportBean> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportBean.class);
		attendanceServiceFacade.updateDailyAttdCloseYN(dailyAttdReportList);
	}
	
	// 일근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public void findDailyAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		String baseDay = inData.getVariable("baseDay").getValue().asString();
		List<DailyAttdReportBean> dailyAttdReportList=attendanceServiceFacade.findDailyAttdReport(baseDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdReportList, DailyAttdReportBean.class);
	}

	// 연장 심야 승인관리 
	public void findOverNightReportByCondition(PlatformData inData, PlatformData outData) throws Exception{
		//System.out.println(inData);
		String deptCode = inData.getVariable("deptCode").getValue().asString();
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		String approvalStatus = inData.getVariable("approvalStatus").getValue().asString();
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
	public void batchOverNight(PlatformData inData, PlatformData outData) throws Exception {
		List<OverNightReportBean> overNightReportList=datasetBeanMapper.datasetToBeans(inData, OverNightReportBean.class);
		attendanceServiceFacade.batchOverNight(overNightReportList);
    }
	
	
	// 해당년월의 일근태관리테이블의 데이터중 미마감인 day관련 정보를 조회하는 항목 
	public void findUnClosedDailyAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		String baseYearMonth = inData.getVariable("baseYearMonth").getValue().asString();
		List<DailyAttdReportBean> UnclosedDailyAttdReportList=attendanceServiceFacade.findUnClosedDailyAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportBean.class);
	}
	
	// 월근태 미마감항목 조회에서 일근태/근태외를 미마감한 경우 일괄적으로 처리하는 메서드 
	public void batchDailyAttdCloseYN(PlatformData inData, PlatformData outData) throws Exception {
		List<DailyAttdReportBean> dailyAttdReportList=datasetBeanMapper.datasetToBeans(inData, DailyAttdReportBean.class);
		List<DailyAttdReportBean> UnclosedDailyAttdReportList=attendanceServiceFacade.batchDailyAttdCloseYN(dailyAttdReportList);
		datasetBeanMapper.beansToDataset(outData, UnclosedDailyAttdReportList, DailyAttdReportBean.class);
	}
	
	
	// 월근태관리테이블에서 기준일에 해당하는 데이터를 생성,조회하는 메서드 
	public void findMonthAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		String baseYearMonth = inData.getVariable("baseYearMonth").getValue().asString();
		List<MonthAttdReportBean> monthAttdReportList=attendanceServiceFacade.findMonthAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportBean.class);
	}
	
	// 기준년월의 데이터를 마감처리하는 메서드 
	public void updateMonthAttdCloseYN(PlatformData inData, PlatformData outData) throws Exception {
		List<MonthAttdReportBean> monthAttdReportList=datasetBeanMapper.datasetToBeans(inData, MonthAttdReportBean.class);
		attendanceServiceFacade.updateMonthAttdCloseYN(monthAttdReportList);
	}

	//월근태 미간 매역 조회 
	public void findClosedMonthAttdReport(PlatformData inData, PlatformData outData) throws Exception {
		String baseYearMonth = inData.getVariable("baseYearMonth").getValue().asString();
		List<MonthAttdReportBean> monthAttdReportList=attendanceServiceFacade.findClosedMonthAttdReport(baseYearMonth);
		datasetBeanMapper.beansToDataset(outData, monthAttdReportList, MonthAttdReportBean.class);
    }



}
