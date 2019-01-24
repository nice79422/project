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
import com.test5th.hr.attendance.to.DayAnnualBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class DayAnnualController{
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	@RequestMapping("hr/attendance/findAnnualMgt.do")
	public void findAnnualMgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String empCode = inData.getVariable("empCode").getString();
		String standardYear = inData.getVariable("standardYear").getString();
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", empCode);
		map.put("standardYear", standardYear);
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.findAnnualMgt(map);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
    }
	
	// 연차를 신청하는 메서드 
	@RequestMapping("hr/attendance/addDayAnnual.do")
	public void addDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		DayAnnualBean dayAnnualBean=datasetBeanMapper.datasetToBean(inData, DayAnnualBean.class);
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.addDayAnnual(dayAnnualBean);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
	}
	
	
	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드
	@RequestMapping("hr/attendance/findDayAnnualListByCondition.do")
	public void findDayAnnualListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String deptCode = inData.getVariable("deptCode").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		ConditionBean conditionBean =new ConditionBean();
		conditionBean.setApprovalStatus(approvalStatus);
		conditionBean.setBasicDay(basicDay);
		conditionBean.setDeptCode(deptCode);
		List<DayAnnualBean> dayAnnualList=attendanceServiceFacade.findDayAnnualListByCondition(conditionBean);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualBean.class);
	  }


	// 연차 승인화면에서 연차를 일괄적으로 승인처리 하는 메서드 
	@RequestMapping("hr/attendance/batchDayAnnual.do")
	public void batchDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DayAnnualBean> dayAnnualList=datasetBeanMapper.datasetToBeans(inData, DayAnnualBean.class);
		attendanceServiceFacade.batchDayAnnual(dayAnnualList);
    }


}
