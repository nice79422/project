package com.test5th.hr.pay.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.pay.serviceFacade.PayServiceFacade;
import com.test5th.hr.pay.to.PayDeductionBean;
import com.test5th.hr.pay.to.SalaryInputBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class PayController{
	/* PayServiceFacade SETTING */
	@Autowired
	private PayServiceFacade payServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 급여를 계산하는 메서드 */
	@RequestMapping("hr/pay/payCalculate.do")
	public void payCalculate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");  
		String paymentDate = inData.getVariable("paymentDate").getString();
		String standardDate = inData.getVariable("standardDate").getString();// 추가 
		
		List<SalaryInputBean> salaryInputList=payServiceFacade.payCalculate(paymentDate,standardDate);
		
		List<PayDeductionBean> payDeductionList=new ArrayList<PayDeductionBean>();
		for(SalaryInputBean salaryInputBean:salaryInputList){
			payDeductionList.addAll(salaryInputBean.getPayDeductionList());
		}
		datasetBeanMapper.beansToDataset(outData, salaryInputList, SalaryInputBean.class);
		datasetBeanMapper.beansToDataset(outData, payDeductionList, PayDeductionBean.class);
    }
}