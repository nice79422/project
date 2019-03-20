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
import kr.co.seoulit.hr.attendance.to.DailyAttdTO;

@Controller
public class DailyAttdController {

	@Autowired
	private AttendanceServiceFacade attendanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	//일근태 목록을 가지고 오는 메서드
	@RequestMapping("hr/attendance/findDailyAttdList.do")
	public void findDailyAttdList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");

		String empCode = inData.getVariable("empCode").getString();
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
        HashMap<String,String> map=new HashMap<>();
        map.put("empCode",empCode);
        map.put("fromDate",fromDate);
        map.put("toDate",toDate);

		List<DailyAttdTO> dailyAttdList=attendanceServiceFacade.findDailyAttdList(map);
		datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdTO.class);
    }
	
	// 일근태 승인, 관리부분에서 조건에 따라 조회하는 메서드 
	@RequestMapping("hr/attendance/findDailyAttdListByCondition.do")
	public void findDailyAttdListByCondition(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String approvalStatus = inData.getVariable("approvalStatus").getString();
		String basicDay = inData.getVariable("basicDay").getString();
		String deptCode = inData.getVariable("deptCode").getString();
		
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("approvalStatus", approvalStatus);
		map.put("basicDay", basicDay);
		map.put("deptCode", deptCode);
		
		List<DailyAttdTO> dailyAttdList=attendanceServiceFacade.findDailyAttdListByCondition(map);
		datasetBeanMapper.beansToDataset(outData, dailyAttdList, DailyAttdTO.class);
	   }
	
	//일근태 등록,수정,삭제 메서드
	@RequestMapping("hr/attendance/batchDailyAttd.do")
	public void batchDailyAttd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<DailyAttdTO> dailyAttdList=datasetBeanMapper.datasetToBeans(inData, DailyAttdTO.class);
		attendanceServiceFacade.batchDailyAttd(dailyAttdList);
    }

}
