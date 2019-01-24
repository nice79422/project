package com.test5th.hr.circumstance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import com.test5th.hr.circumstance.to.DeductionTaxBean;
import com.test5th.hr.circumstance.to.IncomeTaxBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class DeductionInsuranceController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 보험공제 목록을 가져오는 메서드 */
	@RequestMapping("hr/circumstance/findDeductionInsuranceList.do")
	public void findDeductionInsuranceList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<DeductionTaxBean> deductionsTaxList=circumstanceServiceFacade.findDeductionTaxList();
		List<IncomeTaxBean> incomeTaxList=circumstanceServiceFacade.findIncomeTaxList();
		datasetBeanMapper.beansToDataset(outData, deductionsTaxList, DeductionTaxBean.class);
		datasetBeanMapper.beansToDataset(outData, incomeTaxList, IncomeTaxBean.class);
    }
	
	// 보험공제, 소득세를 삭제하는 메서드 
	@RequestMapping("hr/circumstance/removeDeductionTax.do")
	public void removeDeductionTax(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DeductionTaxBean deductionTaxBean=datasetBeanMapper.datasetToBean(inData, DeductionTaxBean.class);
		List<IncomeTaxBean> incomeTaxList=datasetBeanMapper.datasetToBeans(inData, IncomeTaxBean.class);
		circumstanceServiceFacade.removeDeductionTax(deductionTaxBean);
		circumstanceServiceFacade.removeIncomeTaxList(incomeTaxList);
		findDeductionInsuranceList(request,response);
	}

	// 보험공제, 소득세 관련부분을 일괄적으로 처리하는 메서드 
	@RequestMapping("hr/circumstance/batchDeductionTax.do")
	public void batchDeductionTax(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		DeductionTaxBean deductionTaxBean=datasetBeanMapper.datasetToBean(inData, DeductionTaxBean.class);
		List<IncomeTaxBean> incomeTaxList=datasetBeanMapper.datasetToBeans(inData, IncomeTaxBean.class);
		circumstanceServiceFacade.addDeductionTax(deductionTaxBean);
		circumstanceServiceFacade.addIncomeTaxList(incomeTaxList);
		findDeductionInsuranceList(request,response);
	}

	
    
}
