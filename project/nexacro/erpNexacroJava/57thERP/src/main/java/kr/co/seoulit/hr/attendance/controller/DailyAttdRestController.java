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
import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;

@Controller
public class DailyAttdRestController {
	/* AttendanceServiceFacade setting */
	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//근태외 목록을 가지고오는 메서드
	@RequestMapping("hr/attendance/findAttdRestList.do")
	public void findAttdRestList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String empCode = inData.getVariable("empCode").getString();
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("empCode", empCode);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		
		List<DailyAttdRestTO> dailyAttdRestList=attendanceServiceFacade.findAttdRestList(map);
		datasetBeanMapper.beansToDataset(outData, dailyAttdRestList, DailyAttdRestTO.class);
    }
	
	// 근태외 승인부분에서 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/attendance/batchDailyAttdRest.do")
	public void batchDailyAttdRest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DailyAttdRestTO> dailyAttdRestList=datasetBeanMapper.datasetToBeans(inData, DailyAttdRestTO.class);
		attendanceServiceFacade.batchDailyAttdRest(dailyAttdRestList);
    }
	
	// 근태외 승인, 일근태 관리부분에서 조건에 따라 조회하는 메서드 
	@RequestMapping("hr/attendance/findAttdRestListByCondition.do")
	public void findAttdRestListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String deptCode = inData.getVariable("deptCode").getString();
	
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("approvalStatus", approvalStatus);
		map.put("basicDay", basicDay);
		map.put("deptCode", deptCode);
		
		List<DailyAttdRestTO> DailyAttdRestList=attendanceServiceFacade.findAttdRestListByCondition(map);
		datasetBeanMapper.beansToDataset(outData, DailyAttdRestList, DailyAttdRestTO.class);
	}
}
