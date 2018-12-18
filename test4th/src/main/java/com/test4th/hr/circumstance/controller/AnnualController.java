package com.test4th.hr.circumstance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.circumstance.service.CircumstanceServiceFacade;
import com.test4th.hr.circumstance.to.AnnualBean;
import com.tobesoft.platform.data.PlatformData;

public class AnnualController extends AbstractMiplatformMultiActionController{
	/* CircumstanceServiceFacade setting */
	private CircumstanceServiceFacade circumstanceServiceFacade;
	public void setCircumstanceServiceFacade(CircumstanceServiceFacade circumstanceServiceFacade) {
		this.circumstanceServiceFacade = circumstanceServiceFacade;
	}

	/* 사원의 연차정보를 조회하는 메서드 */
	public void findAnnualList(PlatformData inData, PlatformData outData) throws Exception {
	       List<AnnualBean> annualList=circumstanceServiceFacade.findAnnualList();
	       datasetBeanMapper.beansToDataset(outData, annualList, AnnualBean.class);
	}

	// 해당년도, 해당사원의 연차를 생성하는 메서드 
	public void createAnnual(PlatformData inData, PlatformData outData) throws Exception {
		String standardYear = inData.getVariable("standardYear").getValue().asString();
		String empCode = inData.getVariable("empCode").getValue().asString();
		String hireDate = inData.getVariable("hireDate").getValue().asString();
		List<AnnualBean> annulList=circumstanceServiceFacade.createAnnual(standardYear,empCode,hireDate);
		datasetBeanMapper.beansToDataset(outData, annulList, AnnualBean.class);
    }
	
	// 연차와 관련된 일괄처리를 하는 메서드 
	public void batchAnnual(PlatformData inData, PlatformData outData) throws Exception {
		   List<AnnualBean> annualList=datasetBeanMapper.datasetToBeans(inData, AnnualBean.class);
		   circumstanceServiceFacade.batchAnnual(annualList);
		   findAnnualList(inData,outData);
	}
	
	// 연차신청할때 연차관리테이블을 수정하기 위한 메서드 
	public void editAnnualMgt(PlatformData inData, PlatformData outData) throws Exception {
		String standardYear = inData.getVariable("standardYear").getValue().asString();
		String empCode = inData.getVariable("empCode").getValue().asString();
		String days = inData.getVariable("days").getValue().asString();
		String restDays = inData.getVariable("restDays").getValue().asString();
		List<AnnualBean> annulList=circumstanceServiceFacade.editAnnulMgt(standardYear,empCode,days,restDays);
		datasetBeanMapper.beansToDataset(outData, annulList, AnnualBean.class);
    }
    
    
}
