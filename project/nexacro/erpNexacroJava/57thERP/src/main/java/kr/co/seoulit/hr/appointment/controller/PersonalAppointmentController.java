package kr.co.seoulit.hr.appointment.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.xapi.data.PlatformData;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.appointment.serviceFacade.AppointmentServiceFacade;
import kr.co.seoulit.hr.appointment.to.AppointmentTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentDetailTO;
import kr.co.seoulit.hr.appointment.to.PersonalAppointmentTO;
import kr.co.seoulit.hr.appointment.to.TempAppointmentTO;
import kr.co.seoulit.hr.emp.to.EmployeeTO;


@Controller
public class PersonalAppointmentController{
	@Autowired
	private AppointmentServiceFacade appointmentServiceFacade;

	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	@RequestMapping("hr/appointment/findPersonalAppointmentList.do")
	public void findPersonalAppointmentList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		request.getAttribute("inData");
		List<PersonalAppointmentTO> personalAppointmentList = appointmentServiceFacade.findPersonalAppointmentList();
		datasetBeanMapper.beansToDataset(outData, personalAppointmentList, PersonalAppointmentTO.class);
	}
	
	@RequestMapping("hr/appointment/batchPersonalAppointment.do")
	public void batchPersonalAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<PersonalAppointmentTO> personalAppointmentList = datasetBeanMapper.datasetToBeans(inData,PersonalAppointmentTO.class);
		appointmentServiceFacade.batchPersonalAppointment(personalAppointmentList);
	}

	@RequestMapping("hr/appointment/findPersonalAppointmentDetailList.do")
	public void findPersonalAppointmentDetailList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> appointmentDetailMap = new HashMap<>();
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String appointmentNo = inData.getVariable("appointmentNo").getString();
		String employeeNo = inData.getVariable("employeeNo").getString();
		String applyDate = inData.getVariable("applyDate").getString();
		String appointmentHistory = inData.getVariable("appointmentDivision").getString();
		appointmentDetailMap.put("appointmentNo", appointmentNo);
		appointmentDetailMap.put("employeeNo", employeeNo);
		appointmentDetailMap.put("applyDate", applyDate);
		appointmentDetailMap.put("appointmentHistory", appointmentHistory);
		List<PersonalAppointmentDetailTO> personalAppointmentDetailList = appointmentServiceFacade.findPersonalAppointmentDetailList(appointmentDetailMap);
		datasetBeanMapper.beansToDataset(outData, personalAppointmentDetailList, PersonalAppointmentDetailTO.class);
	}	
	
	@RequestMapping("hr/appointment/findPersonalAppointmentDetailList2.do")	//테스트
	public void findPersonalAppointmentDetailList2(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		request.getAttribute("inData");
		List<PersonalAppointmentDetailTO> personalAppointmentDetailList2 = appointmentServiceFacade.findPersonalAppointmentDetailList2();
		datasetBeanMapper.beansToDataset(outData, personalAppointmentDetailList2, PersonalAppointmentDetailTO.class);
	}	
	
	@RequestMapping("hr/appointment/batchPersonalAppointmentDetail.do")
	public void batchPersonalAppointmentDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<AppointmentTO> appointment = datasetBeanMapper.datasetToBeans(inData,AppointmentTO.class);
		List<PersonalAppointmentTO> personalAppointment = datasetBeanMapper.datasetToBeans(inData,PersonalAppointmentTO.class);
		List<PersonalAppointmentDetailTO> personalAppointmentDetail = datasetBeanMapper.datasetToBeans(inData,PersonalAppointmentDetailTO.class);
		List<EmployeeTO> employeeList = datasetBeanMapper.datasetToBeans(inData,EmployeeTO.class);
		appointmentServiceFacade.batchPersonalAppointmentDetail(appointment,personalAppointment,personalAppointmentDetail,employeeList);
	}

	
	
	
	//20190224 프로시저 테스트
	@RequestMapping("hr/appointment/findAppointmentHistory.do")
	public void findAppointmentHistory(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> appointmentNoMap = new HashMap<>();
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String appointmentNo = inData.getVariable("appointmentNo").getString();
		String appointmentDivision = inData.getVariable("appointmentDivision").getString();
		appointmentNoMap.put("appointmentNo", appointmentNo);
		appointmentNoMap.put("appointmentDivision", appointmentDivision);
		appointmentServiceFacade.findAppointmentHistory(appointmentNoMap);
		List<TempAppointmentTO> appointmentHistoryList =(List<TempAppointmentTO>)appointmentNoMap.get("result"); 
		datasetBeanMapper.beansToDataset(outData, appointmentHistoryList, TempAppointmentTO.class);
	}
	
	//20190225 프로시저 테스트
	@RequestMapping("hr/appointment/findCanceledAppointment.do")
	public void findCanceledAppointment(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, Object> appointmentInfoMap = new HashMap<>();
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String appointmentNo = inData.getVariable("appointmentNo").getString();
		String appointmentDivision = inData.getVariable("appointmentDivision").getString();
		appointmentInfoMap.put("appointmentNo", appointmentNo);
		appointmentInfoMap.put("appointmentDivision", appointmentDivision);
		appointmentServiceFacade.findCanceledAppointment(appointmentInfoMap);
		List<TempAppointmentTO> appointmentHistoryList =(List<TempAppointmentTO>)appointmentInfoMap.get("result"); 
		datasetBeanMapper.beansToDataset(outData, appointmentHistoryList, TempAppointmentTO.class);
	}
}
