package com.test4th.hr.attendance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.attendance.service.AttendanceServiceFacade;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DayAnnualBean;
import com.tobesoft.platform.data.PlatformData;

public class DayAnnualController extends AbstractMiplatformMultiActionController{
	/* AttendanceServiceFacade setting */
	private AttendanceServiceFacade attendanceServiceFacade;
	public void setAttendanceServiceFacade(AttendanceServiceFacade attendanceServiceFacade) {
		this.attendanceServiceFacade = attendanceServiceFacade;
	}

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	public void findAnnualMgt(PlatformData inData, PlatformData outData) throws Exception {
		String empCode = inData.getVariable("empCode").getValue().asString();
		String standardYear = inData.getVariable("standardYear").getValue().asString();
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.findAnnualMgt(empCode,standardYear);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
    }
	
	// 연차를 신청하는 메서드 
	public void addDayAnnual(PlatformData inData, PlatformData outData) throws Exception {
		DayAnnualBean dayAnnualBean=datasetBeanMapper.datasetToBean(inData, DayAnnualBean.class);
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.addDayAnnual(dayAnnualBean);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
	}
	
	
	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드 
	public void findDayAnnualListByCondition(PlatformData inData, PlatformData outData) throws Exception {
		String deptCode = inData.getVariable("deptCode").getValue().asString();
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		String approvalStatus = inData.getVariable("approvalStatus").getValue().asString();
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.findDayAnnualListByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
	  }


	// 연차 승인화면에서 연차를 일괄적으로 승인처리 하는 메서드 
	public void batchDayAnnual(PlatformData inData, PlatformData outData) throws Exception {
		List<DayAnnualBean> dayAnnualList=datasetBeanMapper.datasetToBeans(inData, DayAnnualBean.class);
		attendanceServiceFacade.batchDayAnnual(dayAnnualList);
    }


}
