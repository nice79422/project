package com.test4th.base.controller;

import java.util.List;

import com.tobesoft.platform.data.PlatformData;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.EmployeeBean;
import com.test4th.base.to.EmployeeHireDateBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.Dataset;
import com.tobesoft.platform.data.PlatformData;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class BasicEmployeeController extends AbstractMiplatformMultiActionController {
	/* BaseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade){
		this.baseServiceFacade=baseServiceFacade;
	}

	/*(로그인시) 사원 정보 구해오기*/	
	public void findEmployeeList(PlatformData inData, PlatformData outData) throws Exception{
		  
		  List<EmployeeBean> EmployeeList=baseServiceFacade.findEmployeeList();
		  datasetBeanMapper.beansToDataset(outData, EmployeeList, EmployeeBean.class);
	}
	
	
	/* 사원정보 일괄저장하는 메서드  */
	public void batchEmployee(PlatformData inData, PlatformData outData) throws Exception{
		EmployeeBean employeeBean=datasetBeanMapper.datasetToBean(inData, EmployeeBean.class);
		baseServiceFacade.batchEmployee(employeeBean);
		findEmployeeList(inData,outData); // 사원등록 후 dsEmployee dataset을 새롭게 등록해줘야한다. 기존에 만들어 둔걸 이용한다!
	}
	
	
	/*퇴직자 미포함 사원정보 얻기*/	
	public void findFilterEmployeeList(PlatformData inData, PlatformData outData) throws Exception{
		  
		  List<EmployeeBean> EmployeeList=baseServiceFacade.findFilterEmployeeList();
		  datasetBeanMapper.beansToDataset(outData, EmployeeList, EmployeeBean.class);
	}

	/*사원기초정보관리*/
	
	public void batcEditEmployee(PlatformData inData, PlatformData outData) throws Exception{

		List<EmployeeBean> employeeList=datasetBeanMapper.datasetToBeans(inData, EmployeeBean.class);
		baseServiceFacade.batcEditEmployee(employeeList);
		findEmployeeList(inData,outData);
	}
	
	/* 모든 사원의 입사정보를 가지고 오는 메서드 */
	public void findEmployeeHireDateList(PlatformData inData, PlatformData outData) throws Exception{
		  List<EmployeeHireDateBean> employeeHireDateList=baseServiceFacade.findEmployeeHireDateList();
		  datasetBeanMapper.beansToDataset(outData, employeeHireDateList, EmployeeHireDateBean.class);
	}
	
	/* 재직증명서 가져오는 메서드 */
	public void pdfSend(PlatformData inData, PlatformData outData) throws Exception  {
	      String sEmpCode = inData.getVariable("sEmpCode").getValue().asString();
	      System.out.println("!@#$:-----"+sEmpCode);
	        
	      String templatePath = "C:/apache2.2/htdocs/worldMiplatform/report/KJCompany_Report.jrxml";
	       // 출력할 PDF 파일 경로
	      String destPath = "C:/apache2.2/htdocs/worldMiplatform/report/"+sEmpCode+".pdf";
	      Connection con = null;
	      try {
	      //템플레이트 XML 컴파일
	      JasperReport jasperReport = JasperCompileManager.compileReport(templatePath);
	      Map<String,Object> paramMap = new HashMap<>();
	      paramMap.put("sEmpCode",sEmpCode);
	      //getConnection
	      Class.forName("oracle.jdbc.OracleDriver");
	      con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe", "hr1","hr1");
	      //데이타의 동적 바인드
	      JasperPrint print = JasperFillManager.fillReport(jasperReport, paramMap, con);
	      //PDF로 출력
	      JasperExportManager.exportReportToPdfFile(print, destPath);
	      }catch (Exception ex) {
	            ex.printStackTrace();
	      }finally{
	         try {
	            if(con.isClosed() == false){
	               con.close();
	            }
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      
	   }
	
	
	
	
	
	
	
	public void sendEmail(PlatformData inData, PlatformData outData) throws Exception {
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
	         String filename = "C:/apache2.2/htdocs/worldMiplatform/report/"+empCode+".pdf";
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
	   }
}