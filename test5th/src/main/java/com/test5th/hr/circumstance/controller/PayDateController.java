package com.test5th.hr.circumstance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import com.test5th.hr.circumstance.to.SalPaymentDateBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class PayDateController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 귀속년월의 등록된 급/상여 지급일및 관련정보를 조회하는 메서드 */
	@RequestMapping("hr/circumstance/findSalPaymentDateList.do")
	public void findSalPaymentDateList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		String inputedYearMonth = inData.getVariable("inputedYearMonth").getString();
		List<SalPaymentDateBean> salPaymentsDateList = circumstanceServiceFacade.findSalPaymentDateList(inputedYearMonth);
		datasetBeanMapper.beansToDataset(outData, salPaymentsDateList, SalPaymentDateBean.class);
    }


	// 귀속년월의 급/상여 지급일 및 관련정보를 등록및 수정,삭제한 내용을 일괄처리하는 메서드 
	@RequestMapping("hr/circumstance/batchSalPaymentDate.do")
	public void batchSalPaymentDate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		List<SalPaymentDateBean> salPaymentDateList = datasetBeanMapper.datasetToBeans(inData, SalPaymentDateBean.class);
		circumstanceServiceFacade.batchSalPaymentDate(salPaymentDateList);
    }
    
}
