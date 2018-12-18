package com.test4th.hr.attendance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.attendance.service.AttendanceServiceFacade;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdBean;
import com.tobesoft.platform.data.PlatformData;

public class DailyAttdController extends AbstractMiplatformMultiActionController{
	/* AttendanceServiceFacade setting */
	private AttendanceServiceFacade attendanceServiceFacade;
	public void setAttendanceServiceFacade(AttendanceServiceFacade attendanceServiceFacade) {
		this.attendanceServiceFacade = attendanceServiceFacade;
	}

	/* 일근태 목록을 가지고 오는 메서드 */
	public void findDailyAttdList(PlatformData inData, PlatformData outData) throws Exception {
		String empCode = inData.getVariable("empCode").getValue().asString();
		List<DailyAttdBean> dailyAttdList=attendanceServiceFacade.findDailyAttdList(empCode);
		datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdBean.class);
    }

	// 일근태를 추가하는 메서드 
	public void addDailyAttd(PlatformData inData, PlatformData outData) throws Exception {
		DailyAttdBean dailyAttdBean=datasetBeanMapper.datasetToBean(inData, DailyAttdBean.class);
		List<DailyAttdBean> dailyAttdList=attendanceServiceFacade.addDailyAttd(dailyAttdBean);
		datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdBean.class);
    }
	
	
	// 일근태 승인, 관리부분에서 조건에 따라 조회하는 메서드 
	public void findDailyAttdListByCondition(PlatformData inData, PlatformData outData) throws Exception {
		String deptCode = inData.getVariable("deptCode").getValue().asString();
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		String approvalStatus = inData.getVariable("approvalStatus").getValue().asString();
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<DailyAttdBean> dailyAttdList=attendanceServiceFacade.findDailyAttdListByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdBean.class);
	   }
	
	//일근태 관리에서 근태/근태외 미승인을 승인으로 변경 후 저장 할 때 
	public void updateApproval(PlatformData inData, PlatformData outData) throws Exception {
		batchDailyAttd(inData,outData);
		String basicDay = inData.getVariable("basicDay").getValue().asString();
		List<DailyAttdBean> unApprovedDailyAttdList=attendanceServiceFacade.findUnApprovedDailyAttdList(basicDay); //변경 후 결과를 가져 간다.
		datasetBeanMapper.beansToDataset(outData, unApprovedDailyAttdList, DailyAttdBean.class);
    }
		
		

	// 일근태 승인화면에서 일근태를 일괄적으로 승인처리 하는 메서드 (N- > Y 변경 하는 곳 )
	public void batchDailyAttd(PlatformData inData, PlatformData outData) throws Exception {
		List<DailyAttdBean> dailyAttdList=datasetBeanMapper.datasetToBeans(inData, DailyAttdBean.class);
		attendanceServiceFacade.batchDailyAttd(dailyAttdList);
    }


	
	
}
