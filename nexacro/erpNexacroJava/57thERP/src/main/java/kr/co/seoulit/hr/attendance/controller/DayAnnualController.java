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
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;



@Controller
public class DayAnnualController {
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	@RequestMapping("hr/attendance/findAnnualMgt.do")
	private void findAnnualMgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String empCode = inData.getVariable("empCode").getString();
		String standardYear = inData.getVariable("standardYear").getString();
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", empCode);
		map.put("standardYear", standardYear);
		List<DayAnnualTO> dayAnnualList=attendanceServiceFacade.findAnnualMgt(map);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
	}
	
	// 연차를 신청하는 메서드 
	@RequestMapping("hr/attendance/addDayAnnual.do")
	public void addDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		DayAnnualTO dayAnnualTO=datasetBeanMapper.datasetToBean(inData, DayAnnualTO.class);
		List<DayAnnualTO> dayAnnualList=attendanceServiceFacade.addDayAnnual(dayAnnualTO);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
	}
	
	// 연차 승인, 관리부분에서 조건에 따라 조회하는 메서드
	@RequestMapping("hr/attendance/findDayAnnualListByCondition.do")
	public void findDayAnnualListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String deptCode = inData.getVariable("deptCode").getString();
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("deptCode", deptCode);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		map.put("approvalStatus", approvalStatus);
		
		List<DayAnnualTO> dayAnnualList=attendanceServiceFacade.findDayAnnualListByCondition(map);
		datasetBeanMapper.beansToDataset(outData, dayAnnualList, DayAnnualTO.class);
	  }
	
	//연차를 일괄적으로 승인처리 하는 메서드 
	@RequestMapping("hr/attendance/batchDayAnnual.do")
	public void batchDayAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DayAnnualTO> dayAnnualList=datasetBeanMapper.datasetToBeans(inData, DayAnnualTO.class);
		attendanceServiceFacade.batchDayAnnual(dayAnnualList);
    }
}
