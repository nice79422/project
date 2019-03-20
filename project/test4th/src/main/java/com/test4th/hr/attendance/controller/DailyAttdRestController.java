package com.test4th.hr.attendance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.attendance.service.AttendanceServiceFacade;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdRestBean;
import com.tobesoft.platform.data.PlatformData;

public class DailyAttdRestController extends AbstractMiplatformMultiActionController{
	/* AttendanceServiceFacade setting */
	private AttendanceServiceFacade attendanceServiceFacade;
	public void setAttendanceServiceFacade(AttendanceServiceFacade attendanceServiceFacade) {
		this.attendanceServiceFacade = attendanceServiceFacade;
	}

	/* 근태외 목록을 가지고오는 메서드 */
	public void findAttdRestList(PlatformData inData, PlatformData outData) throws Exception {
		String empCode = inData.getVariable("empCode").getValue().asString();
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.findAttdRestList(empCode);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }
	
	// 근태외신청을 추가하는 메서드 
	public void addDailyAttdRest(PlatformData inData, PlatformData outData) throws Exception {
		DailyAttdRestBean dailyAttdRestBean=datasetBeanMapper.datasetToBean(inData, DailyAttdRestBean.class);
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.addDailyAttdRest(dailyAttdRestBean);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }
	
	// 근태외 승인, 일근태 관리부분에서 조건에 따라 조회하는 메서드 
	public void findAttdRestListByCondition(PlatformData inData, PlatformData outData) throws Exception {
		String deptCode = inData.getVariable("deptCode").getValue().asString();
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		String approvalStatus = inData.getVariable("approvalStatus").getValue().asString();
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<DailyAttdRestBean> DailyAttdRestList=attendanceServiceFacade.findAttdRestListByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, DailyAttdRestList, DailyAttdRestBean.class);
	}
		
	//일근태외 미승인 -> 승인 된 후 목록 던짐 
	public void updateApproval(PlatformData inData, PlatformData outData) throws Exception {
		batchDailyAttdRest(inData,outData);
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.findUnApprovedDailyAttdRestList(basicDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }

	// 근태외 승인부분에서 일괄적으로 처리하는 메서드 
	public void batchDailyAttdRest(PlatformData inData, PlatformData outData) throws Exception {
		List<DailyAttdRestBean> dailyAttdRestList=datasetBeanMapper.datasetToBeans(inData, DailyAttdRestBean.class);
		attendanceServiceFacade.batchDailyAttdRest(dailyAttdRestList);
    }

	
	
}
