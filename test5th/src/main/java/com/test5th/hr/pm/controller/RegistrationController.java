package com.test5th.hr.pm.controller;

import java.io.BufferedOutputStream;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.pm.serviceFacade.PMServiceFacade;
import com.test5th.hr.pm.to.EducationInfoBean;
import com.test5th.hr.pm.to.EmpInfoBean;
import com.test5th.hr.pm.to.EmployeeInfoBean;
import com.test5th.hr.pm.to.FamilyInfoBean;
import com.test5th.hr.pm.to.LicenseInfoBean;
import com.test5th.hr.pm.to.ReportBean;
import com.test5th.hr.pm.to.SalInfoBean;
import com.test5th.hr.pm.to.WorkInfoBean;
import com.tobesoft.xplatform.data.DataSet;
import com.tobesoft.xplatform.data.PlatformData;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class RegistrationController{
	/* PMServiceFacade setting */
	@Autowired
	private PMServiceFacade pmServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	// 해당사원의 모든 사원관련 상세정보를 가져오는 메서드 
	@RequestMapping("hr/pm/findEmployeeInfoAll.do")
	public void findEmployeeInfoAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		PlatformData outData = (PlatformData) request.getAttribute("outData");  
		EmployeeInfoBean employeeInfoBean=pmServiceFacade.findEmployeeInfoAll();
		
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getEmpInfoList(), EmpInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getWorkInfoList(), WorkInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getFamilyInfoList(), FamilyInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getLicenseInfoList(), LicenseInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getEducationInfoList(), EducationInfoBean.class);
		datasetBeanMapper.beansToDataset(outData, employeeInfoBean.getSalInfoList(), SalInfoBean.class);
		
		
		
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
				out = new FileOutputStream("C:/workspace-sts-3.9.6.RELEASE/test5th/src/main/webapp/img" + fileName);
				
				
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
		EmployeeInfoBean employeeInfoBean=new EmployeeInfoBean();
		employeeInfoBean.setEmpInfoList(datasetBeanMapper.datasetToBeans(inData, EmpInfoBean.class));
		employeeInfoBean.setWorkInfoList(datasetBeanMapper.datasetToBeans(inData, WorkInfoBean.class));
		employeeInfoBean.setFamilyInfoList(datasetBeanMapper.datasetToBeans(inData, FamilyInfoBean.class));
		employeeInfoBean.setLicenseInfoList(datasetBeanMapper.datasetToBeans(inData, LicenseInfoBean.class));
		employeeInfoBean.setEducationInfoList(datasetBeanMapper.datasetToBeans(inData, EducationInfoBean.class));
	
		employeeInfoBean.setSalInfoList(datasetBeanMapper.datasetToBeans(inData, SalInfoBean.class));
		
		
		pmServiceFacade.batchEmployeeInfoAll(employeeInfoBean);
		findEmployeeInfoAll(request,response);
    }

	//재직증명서 pdf 
	@RequestMapping("hr/pm/pdfPrintEmp.do")
	public ModelAndView pdfPrintEmp(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String empCode = request.getParameter("empCode");
		String format = request.getParameter("format");
		System.out.println("empCode : "+empCode);
		System.out.println("format : "+format);
		List<ReportBean> reportList = pmServiceFacade.findEmpInfoReport(empCode);
		JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(reportList);

		ModelMap modelMap = new ModelMap();
		modelMap.put("format", format);
		modelMap.put("source", source);
		ModelAndView modelAndView = new ModelAndView("multiformat-view", modelMap);
		//request.setAttribute("outData", null);
		return modelAndView;
	}
	

	//email 보내기
	   @RequestMapping("hr/pm/sendEmail.do")
	   public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      PlatformData inData = (PlatformData) request.getAttribute("inData"); 
	        String empCode = inData.getVariable("sEmpCode").getString();
	         String sEmail = inData.getVariable("sEmail").getString();
	         // 메일 관련 정보
	         
	         System.out.println(empCode);
	         System.out.println(sEmail);
	         
	            String host = "smtp.naver.com";
	            final String username = "";               //네이버 아이디
	            final String password = "";      //비밀번호
	            int port=465;
	             
	            // 메일 내용
	            String recipient = sEmail;                   //받는 사람의 이메일
	            String subject = empCode+"님의 재직 증명서 입니다.";    //이메일 제목
	            String body = "확인 후 회신바랍니다";                //이메일 내용
	             
	            Properties props = System.getProperties();
	             
	            props.put("mail.smtp.host", host);
	            props.put("mail.smtp.port", port);
	            props.put("mail.smtp.auth", "true");
	            props.put("mail.smtp.ssl.enable", "true");
	            props.put("mail.smtp.ssl.trust", host);
	              
	            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
	                String un=username;
	                String pw=password;
	                protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(un, pw);
	                }
	            });
	            session.setDebug(true);
	              
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress("nice79422@naver.com","테스트발송"));    //첫인자가 보내는 사람 이메일, 두번째 인자가 보내는 사람 이름
	            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	            msg.setSubject(subject);
	             
	            // 파일첨부를 위한 Multipart
	            Multipart multipart = new MimeMultipart();
	             
	            // BodyPart를 생성
	            BodyPart bodyPart = new MimeBodyPart();
	            bodyPart.setText(body);
	             
	            //Multipart에 BodyPart를 붙인다.
	            multipart.addBodyPart(bodyPart);
	             
	            //이미지를 첨부한다.
	            bodyPart = new MimeBodyPart();
	            String filename = "C:/workspace-sts-3.9.6.RELEASE/test5th/src/main/webapp/report/"+empCode+".pdf";
	            //첨부할 파일 경로
	            FileDataSource source = new FileDataSource(filename);
	            bodyPart.setDataHandler(new DataHandler(source));
	            
	            bodyPart.setFileName(empCode+"_salary.pdf");
	            //첨부할 파일의 이름을 바꿔서 보낼수 있음
	            
	            bodyPart.setHeader("Content-ID", "image_id");
	            multipart.addBodyPart(bodyPart);
	             
	            // 이메일 메시지의 내용에 Multipart를 붙인다.
	            msg.setContent(multipart);
	            Transport.send(msg);
	      }
	
}