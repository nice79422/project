package com.test5th.base.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.EmployeeBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class LoginController{
	/* baseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	@RequestMapping("base/checkLogin.do")
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String businessPlaceCode= inData.getVariable("businessPlaceCode").getString();
		String deptCode= inData.getVariable("deptCode").getString();
		String empCode= inData.getVariable("empCode").getString();
		String password= inData.getVariable("password").getString();
		System.out.println(businessPlaceCode);
		System.out.println(deptCode);
		System.out.println(empCode);
		System.out.println(password);
		
		EmployeeBean employee=baseServiceFacade.checkLogin(businessPlaceCode,deptCode,empCode,password);
		  datasetBeanMapper.beanToDataset(outData, employee, EmployeeBean.class);
		 
    }
	

	
}
