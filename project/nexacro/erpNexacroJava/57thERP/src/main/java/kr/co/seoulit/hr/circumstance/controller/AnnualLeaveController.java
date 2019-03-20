package kr.co.seoulit.hr.circumstance.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;


@Controller
public class AnnualLeaveController{
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/* 사원의 연차정보를 조회하는 메서드 */
	@RequestMapping("hr/circumstance/findAnnualLeaveList.do")
	public void findAnnualLeaveList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");	   
		List<AnnualLeaveTO> anuualLeaveList=circumstanceServiceFacade.findAnnualLeaveList();
		for(AnnualLeaveTO aa:anuualLeaveList) {
			System.out.println("aaaaaaa"+aa.getStandardYear());		
		}
		
	       datasetBeanMapper.beansToDataset(outData, anuualLeaveList, AnnualLeaveTO.class);
	}

	// 해당년도, 해당사원의 연차를 생성하는 메서드 
	@RequestMapping("hr/circumstance/createAnnualLeave.do")
	public void createAnnualLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		
		String standardYear = inData.getVariable("standardYear").getString();
		String empCode = inData.getVariable("empCode").getString();
		String hireDate = inData.getVariable("hireDate").getString();
		
		HashMap<String, Object> map=new HashMap<>();
		map.put("standardYear", standardYear);
		map.put("empCode", empCode);
		map.put("hireDate", hireDate);		
		
		List<AnnualLeaveTO> anuualLeaveList=circumstanceServiceFacade.createAnnualLeave(map);
		datasetBeanMapper.beansToDataset(outData, anuualLeaveList, AnnualLeaveTO.class);
    }
	
	// 연차와 관련된 일괄처리를 하는 메서드 
	@RequestMapping("hr/circumstance/batchAnnualLeave.do")
	public void batchAnnualLeave(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");	   
		List<AnnualLeaveTO> annualLeaveList=datasetBeanMapper.datasetToBeans(inData, AnnualLeaveTO.class);
		circumstanceServiceFacade.batchAnnualLeave(annualLeaveList);
		findAnnualLeaveList(request,response);
	}
	
	// 연차신청할때 연차관리테이블을 수정하기 위한 메서드 
	@RequestMapping("hr/circumstance/editAnnualLeaveMgt.do")
	public void editAnnualLeaveMgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		
		String standardYear = inData.getVariable("standardYear").getString();
		String empCode = inData.getVariable("empCode").getString();
		String days = inData.getVariable("days").getString();
		String restDays = inData.getVariable("restDays").getString();

		HashMap<String, Object> map=new HashMap<>();
		map.put("standardYear", standardYear);
		map.put("empCode", empCode);
		map.put("days", days);
		map.put("restDays", restDays);
		
		List<AnnualLeaveTO> annualLeaveList=circumstanceServiceFacade.editAnnualLeaveMgt(map);
		datasetBeanMapper.beansToDataset(outData, annualLeaveList, AnnualLeaveTO.class);
    }
}
