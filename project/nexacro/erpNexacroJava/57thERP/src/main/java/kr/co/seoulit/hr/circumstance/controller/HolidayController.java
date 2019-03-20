package kr.co.seoulit.hr.circumstance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;


@Controller
public class HolidayController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* 선택된 기간의 휴일목록을 가져오는 메서드 */
	@RequestMapping("hr/circumstance/findHolidayList.do")
	public void findHolidayList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String fromDate = inData.getVariable("fromDate").getString();
		String toDate = inData.getVariable("toDate").getString();
		
		System.out.println("fromDate----"+fromDate+"---toDate-----"+toDate);
		
		HashMap<String, Object> dateList=new HashMap<>();
		dateList.put("fromDate", fromDate);
		dateList.put("toDate", toDate);
		
		List<HolidayTO> holidayList=circumstanceServiceFacade.findHolidayList(dateList);
		datasetBeanMapper.beansToDataset(outData, holidayList, HolidayTO.class);
    }
	
	// 휴일관련 일괄처리를 하는 메서드 
	@RequestMapping("hr/circumstance/batchHoliday.do")
	public void batchHoliday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<HolidayTO> holidayList=datasetBeanMapper.datasetToBeans(inData, HolidayTO.class);		
		circumstanceServiceFacade.batchHoliday(holidayList);
		//findHolidayListAll(inData,outData);
    }
	
	

	// 모든 휴일목록을 가져오는 메서드 
	@RequestMapping("hr/circumstance/findHolidayListAll.do")
	public void findHolidayListAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<HolidayTO> holidayList=circumstanceServiceFacade.findHolidayListAll();
		datasetBeanMapper.beansToDataset(outData, holidayList, HolidayTO.class);
    }
	
	// 휴일 등록 메서드 
	@RequestMapping("hr/circumstance/addHoliday.do")
	public void addHoliday(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String basicDay = inData.getVariable("basicDay").getString();
		String holidayName = inData.getVariable("holidayName").getString();
		String holidayType = inData.getVariable("holidayType").getString();
		String note = inData.getVariable("note").getString();
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("basicDay", basicDay);
		map.put("holidayName", holidayName);
		map.put("holidayType", holidayType);		
		map.put("note", note);	
		
		List<HolidayTO> holidayList=circumstanceServiceFacade.addHoliday(map);
		datasetBeanMapper.beansToDataset(outData, holidayList, HolidayTO.class);
    }
	
}
