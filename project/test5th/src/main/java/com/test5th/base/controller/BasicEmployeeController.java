package com.test5th.base.controller;



import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.EmployeeBean;
import com.test5th.base.to.EmployeeHireDateBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;





@Controller
public class BasicEmployeeController{
	/* BaseServiceFacade setting */

	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
	/*사원리스트 조회하기*/
	@RequestMapping("base/findEmployeeList.do")
	public void findEmployeeList(HttpServletRequest  request, HttpServletResponse  response) throws Exception{
		  PlatformData outData = (PlatformData) request.getAttribute("outData");
		  List<EmployeeBean> EmployeeList=baseServiceFacade.findEmployeeList();
		  datasetBeanMapper.beansToDataset(outData, EmployeeList, EmployeeBean.class);
	}
	
	
	/* 사원정보 일괄저장하는 메서드  */
	@RequestMapping("base/batchEmployee.do")
	public void batchEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		EmployeeBean employeeBean=datasetBeanMapper.datasetToBean(inData, EmployeeBean.class);
		baseServiceFacade.batchEmployee(employeeBean);
		findEmployeeList(request,response); // 사원등록 후 dsEmployee dataset을 새롭게 등록해줘야한다. 기존에 만들어 둔걸 이용한다!
	}
	
	
	/*퇴직자 미포함 사원정보 얻기*/	
	@RequestMapping("base/findFilterEmployeeList.do")
	public void findFilterEmployeeList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  PlatformData outData = (PlatformData) request.getAttribute("outData");
		  List<EmployeeBean> EmployeeList=baseServiceFacade.findFilterEmployeeList();
		  datasetBeanMapper.beansToDataset(outData, EmployeeList, EmployeeBean.class);
	}

	/*사원기초정보관리*/
	@RequestMapping("base/batcEditEmployee.do")
	public void batcEditEmployee(HttpServletRequest request, HttpServletResponse response) throws Exception{
        
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<EmployeeBean> employeeList=datasetBeanMapper.datasetToBeans(inData, EmployeeBean.class);
		baseServiceFacade.batcEditEmployee(employeeList);
		findEmployeeList(request,response);
	}
	
	/* 모든 사원의 입사정보를 가지고 오는 메서드 */
	@RequestMapping("base/findEmployeeHireDateList.do")
	public void findEmployeeHireDateList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		  PlatformData outData = (PlatformData) request.getAttribute("outData");	
		  List<EmployeeHireDateBean> employeeHireDateList=baseServiceFacade.findEmployeeHireDateList();
		  datasetBeanMapper.beansToDataset(outData, employeeHireDateList, EmployeeHireDateBean.class);
	}
	

/*
	public void sendEmail(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      String empCode = inData.getVariable("sEmpCode").getValue().asString();
	      String sEmail = inData.getVariable("sEmail").getValue().asString();
	      // 메일 관련 정보
	         String host = "smtp.naver.com";
	         final String username = "you_in3575";               //네이버 아이디
	         final String password = "soohyun~!8149";      //비밀번호
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
	         msg.setFrom(new InternetAddress("you_in3575@naver.com","테스트발송"));    //첫인자가 보내는 사람 이메일, 두번째 인자가 보내는 사람 이름
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
	         String filename = "C:\workspace-sts-3.9.6.RELEASE\test5th\src\main\webapp\report"+empCode+".pdf";
	         //첨부할 파일 경로
	         FileDataSource source = new FileDataSource(filename);
	         bodyPart.setDataHandler(new DataHandler(source));
	         
	         bodyPart.setFileName(empCode+"_재직증명서.pdf");
	         //첨부할 파일의 이름을 바꿔서 보낼수 있음
	         
	         bodyPart.setHeader("Content-ID", "image_id");
	         multipart.addBodyPart(bodyPart);
	          
	         // 이메일 메시지의 내용에 Multipart를 붙인다.
	         msg.setContent(multipart);
	         Transport.send(msg);
	   }*/
	
	
	
	
}