package com.test5th.hr.attendance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.attendance.serviceFacade.AttendanceServiceFacade;
import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdRestBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class DailyAttdRestController{
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 근태외 목록을 가지고오는 메서드 */
	@RequestMapping("hr/attendance/findAttdRestList.do")
	public void findAttdRestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String empCode = inData.getVariable("empCode").getString();
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.findAttdRestList(empCode);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }
	
	// 근태외신청을 추가하는 메서드 
	@RequestMapping("hr/attendance/addDailyAttdRest.do")
	public void addDailyAttdRest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		DailyAttdRestBean dailyAttdRestBean=datasetBeanMapper.datasetToBean(inData, DailyAttdRestBean.class);
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.addDailyAttdRest(dailyAttdRestBean);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }
	
	// 근태외 승인, 일근태 관리부분에서 조건에 따라 조회하는 메서드 
	@RequestMapping("hr/attendance/findAttdRestListByCondition.do")
	public void findAttdRestListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String deptCode = inData.getVariable("deptCode").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<DailyAttdRestBean> DailyAttdRestList=attendanceServiceFacade.findAttdRestListByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, DailyAttdRestList, DailyAttdRestBean.class);
	}
		
	//일근태외 미승인 -> 승인 된 후 목록 던짐 
	@RequestMapping("hr/attendance/updateRestApproval.do")
	public void updateApproval(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		batchDailyAttdRest(request,response);
		String basicDay = inData.getVariable("basicDay").getString();
		List<DailyAttdRestBean> dailyAttdRestList=attendanceServiceFacade.findUnApprovedDailyAttdRestList(basicDay);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestBean.class);
    }

	// 근태외 승인부분에서 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/attendance/batchDailyAttdRest.do")
	public void batchDailyAttdRest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DailyAttdRestBean> dailyAttdRestList=datasetBeanMapper.datasetToBeans(inData, DailyAttdRestBean.class);
		attendanceServiceFacade.batchDailyAttdRest(dailyAttdRestList);
    }

	
	
}
