package kr.co.seoulit.hr.pm.controller;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Multipart;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeBodyPart;
//import javax.mail.internet.MimeMessage;
//import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nexacro.xapi.data.DataSet;
import com.nexacro.xapi.data.PlatformData;
//import kr.co.seoulit.common.email.EmailManagement;

import kr.co.seoulit.common.mapper.DatasetBeanMapper;
import kr.co.seoulit.hr.emp.to.EmployeeTO;
import kr.co.seoulit.hr.pm.serviceFacade.PMServiceFacade;
import kr.co.seoulit.hr.pm.to.EducationInfoTO;
import kr.co.seoulit.hr.pm.to.EmpInfoTO;
import kr.co.seoulit.hr.pm.to.EmployeeInfoTO;
import kr.co.seoulit.hr.pm.to.FamilyInfoTO;
import kr.co.seoulit.hr.pm.to.LicenseInfoTO;
import kr.co.seoulit.hr.pm.to.SalInfoTO;
import kr.co.seoulit.hr.pm.to.WorkInfoTO;
import kr.co.seoulit.hr.pm.to.ReportTO;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class RegistrationController{
	/* PMServiceFacade setting */
	@Autowired
	private PMServiceFacade pmServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	/*@Autowired
	private EmailManagement emailManagement;*/

	
	// 사원관련 정보를 가져오는 메서드 
	@RequestMapping("hr/pm/findEmpInfoList.do") 
	public void findEmpInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		request.getAttribute("inData");

		List<EmpInfoTO> empInfoList = pmServiceFacade.findEmpInfoList();

		datasetBeanMapper.beansToDataset(outData, empInfoList, EmpInfoTO.class);
	}
	
	// 사원 한명의 코드를 가져오는 메서드 
	@RequestMapping("hr/pm/findEmpCode.do") 
	public void findEmpCode(HttpServletRequest request, HttpServletResponse response) throws Exception{

	      PlatformData inData = (PlatformData) request.getAttribute("inData");
	      PlatformData outData = (PlatformData) request.getAttribute("outData");

	      String empCode = inData.getVariable("empCode").getString();
	      HashMap<String,String> map=new HashMap<>();
	      map.put("empCode",empCode);
	      List<EmpInfoTO> EmpCodeList=pmServiceFacade.findEmpCode(map);
	      datasetBeanMapper.beansToDataset(outData, EmpCodeList, EmpInfoTO.class);
				
		}
				
	
	
	// 사원관련 정보 저장하는 메서드
	@RequestMapping("hr/pm/batchEmpInfoList.do")
	public void batchEmpInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception{

		request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");

		List<EmpInfoTO> batchEmpInfo = datasetBeanMapper.datasetToBeans(inData, EmpInfoTO.class);

		pmServiceFacade.batchEmpInfoList(batchEmpInfo);
		/*saveEmpImg(request,response);*/
	}
				

	// 해당사원의 모든 사원관련 상세정보를 가져오는 메서드 
	@RequestMapping("hr/pm/findEmployeeInfoAll.do")
	public void findEmployeeInfoAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PlatformData outData = (PlatformData) request.getAttribute("outData");  
		EmployeeInfoTO employeeInfoTO=pmServiceFacade.findEmployeeInfoAll();
		
		datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getEmpInfoList(), EmpInfoTO.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getWorkInfoList(), WorkInfoTO.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getFamilyInfoList(), FamilyInfoTO.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getLicenseInfoList(), LicenseInfoTO.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getEducationInfoList(), EducationInfoTO.class);
		//datasetBeanMapper.beansToDataset(outData, employeeInfoTO.getSalInfoList(), SalInfoTO.class);
    }

	// 사원의 이미지를 저장하는 메서드 
	@RequestMapping("hr/pm/saveEmpImg.do")
	public void saveEmpImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DataSet dataset = inData.getDataSet("dsImg");
		FileOutputStream out = null;
		String fileName = dataset.getString(0, "EMP_FILE_NAME");
		try {
			if (fileName != null) {
				out = new FileOutputStream("E:/miplatformpj/unicompany/src/main/webapp/img/" + fileName);
				
				byte[] file = dataset.getBlob(0, "IMG_FILE_DATA");
				BufferedOutputStream bufferedOut = new BufferedOutputStream(out);
				bufferedOut.write(file);
				bufferedOut.flush();
				bufferedOut.close();
				out.close();
				bufferedOut = null;
				out = null;
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	
	// 사원의 상세정보를 일괄처리하는 메서드
	@RequestMapping("hr/pm/batchEmployeeInfoAll.do")
	public void batchEmployeeInfoAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		System.out.println(inData);
		EmployeeInfoTO employeeInfoTO=new EmployeeInfoTO();
		//employeeInfoTO.setEmpInfoList(datasetBeanMapper.datasetToBeans(inData, EmpInfoTO.class));
		//employeeInfoTO.setWorkInfoList(datasetBeanMapper.datasetToBeans(inData, WorkInfoTO.class));
		employeeInfoTO.setFamilyInfoList(datasetBeanMapper.datasetToBeans(inData, FamilyInfoTO.class));
		//employeeInfoTO.setLicenseInfoList(datasetBeanMapper.datasetToBeans(inData, LicenseInfoTO.class));
		employeeInfoTO.setEducationInfoList(datasetBeanMapper.datasetToBeans(inData, EducationInfoTO.class));
	
		//employeeInfoTO.setSalInfoList(datasetBeanMapper.datasetToBeans(inData, SalInfoTO.class));
		
		pmServiceFacade.batchEmployeeInfoAll(employeeInfoTO);
		findEmployeeInfoAll(request,response);
    }
	

	//재직증명서 pdf 
	@RequestMapping("hr/pm/pdfPrintEmp.do")
	public ModelAndView pdfPrintEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String empCode = request.getParameter("empCode");
		String format = request.getParameter("format");
		System.out.println("empCode : "+empCode);
		System.out.println("format : "+format);
		List<ReportTO> reportList = pmServiceFacade.findEmpInfoReport(empCode);
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(reportList);

		ModelMap modelMap = new ModelMap();
		modelMap.put("format", format);
		modelMap.put("source", source);
		ModelAndView modelAndView = new ModelAndView("multiformat-view", modelMap);
		//request.setAttribute("outData", null);
		return modelAndView;
	}
	
	
	//email 보내기
	/*		@RequestMapping("hr/pm/sendEmail.do")
			public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
				PlatformData inData = (PlatformData) request.getAttribute("inData"); 
				  String empCode = inData.getVariable("sEmpCode").getString();
			      String sEmail = inData.getVariable("sEmail").getString();
			      emailManagement.sendEmailMgt(empCode,sEmail);
			}    
	 */	

	// WorkInfo 테이블 정보를 조회
	@RequestMapping("hr/pm/findWorkInfoAll.do")
	public void findWorkInfoAll(HttpServletRequest request, HttpServletResponse response) throws Exception{

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		request.getAttribute("inData");

		List<WorkInfoTO> workInfoList = pmServiceFacade.selectWorkInfoAll();

		datasetBeanMapper.beansToDataset(outData, workInfoList, WorkInfoTO.class);

	}
	
}