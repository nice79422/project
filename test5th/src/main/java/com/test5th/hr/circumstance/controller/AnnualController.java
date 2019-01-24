package com.test5th.hr.circumstance.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import com.test5th.hr.circumstance.to.AnnualBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class AnnualController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 사원의 연차정보를 조회하는 메서드 */
	@RequestMapping("hr/circumstance/findAnnualList.do")
	public void findAnnualList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");	   
		List<AnnualBean> annualList=circumstanceServiceFacade.findAnnualList();
		for(AnnualBean aa:annualList) {
		System.out.println("aaaaaaa"+aa.getStandardYear());		
		}
		
	       datasetBeanMapper.beansToDataset(outData, annualList, AnnualBean.class);
	}

	// 해당년도, 해당사원의 연차를 생성하는 메서드 
	@RequestMapping("hr/circumstance/createAnnual.do")
	public void createAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String standardYear = inData.getVariable("standardYear").getString();
		String empCode = inData.getVariable("empCode").getString();
		String hireDate = inData.getVariable("hireDate").getString();
		List<AnnualBean> annulList=circumstanceServiceFacade.createAnnual(standardYear,empCode,hireDate);
		datasetBeanMapper.beansToDataset(outData, annulList, AnnualBean.class);
    }
	
	// 연차와 관련된 일괄처리를 하는 메서드 
	@RequestMapping("hr/circumstance/batchAnnual.do")
	public void batchAnnual(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");	   
		List<AnnualBean> annualList=datasetBeanMapper.datasetToBeans(inData, AnnualBean.class);
		   circumstanceServiceFacade.batchAnnual(annualList);
		   findAnnualList(request,response);
	}
	
	// 연차신청할때 연차관리테이블을 수정하기 위한 메서드 
	@RequestMapping("hr/circumstance/editAnnualMgt.do")
	public void editAnnualMgt(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");	
		String standardYear = inData.getVariable("standardYear").getString();
		String empCode = inData.getVariable("empCode").getString();
		String days = inData.getVariable("days").getString();
		String restDays = inData.getVariable("restDays").getString();
		List<AnnualBean> annulList=circumstanceServiceFacade.editAnnulMgt(standardYear,empCode,days,restDays);
		datasetBeanMapper.beansToDataset(outData, annulList, AnnualBean.class);
    }
    
    
}
