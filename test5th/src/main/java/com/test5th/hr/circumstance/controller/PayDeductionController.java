package com.test5th.hr.circumstance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import com.test5th.hr.circumstance.to.PayDeductionBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class PayDeductionController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	// 지급/공제항목을 조회하는 메서드 
	@RequestMapping("hr/circumstance/findPayDeductionList.do")
	public void findPayDeductionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<PayDeductionBean> payDeductionList = circumstanceServiceFacade.findPayDeductionList();
		datasetBeanMapper.beansToDataset(outData, payDeductionList, PayDeductionBean.class);
    }
	
	// 지급/공제항목에 관한 일괄처리하는 메서드 
	@RequestMapping("hr/circumstance/batchPayDeduction.do")
	public void batchPayDeduction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		 
		List<PayDeductionBean> payDeductionList=datasetBeanMapper.datasetToBeans(inData, PayDeductionBean.class);
		   circumstanceServiceFacade.batchPayDeduction(payDeductionList);
		   findPayDeductionList(request,response);
	}
	
}
