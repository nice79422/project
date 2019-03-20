package com.test5th.base.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.CompanyBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class CompanyController {
	/* baseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

    /* 회사 조회하는 메서드 */
	@RequestMapping("base/findCompany.do")
	public void findCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		CompanyBean companyBean=baseServiceFacade.findCompany();
        datasetBeanMapper.beanToDataset(outData, companyBean, CompanyBean.class);
        /*
         * 	beansToDataset : bean --> dataset
         * */
    }
	
	/*회사관리 (등록/삭제/수정)*/
	@RequestMapping("base/batchCompany.do")
	public void batchCompany(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * datasetToBean도 있다.
		 *
		 *  datasetToBeans : dataset --> bean
		 *
		 * */
		
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		CompanyBean companyBean=datasetBeanMapper.datasetToBean(inData, CompanyBean.class);
        baseServiceFacade.batchCompany(companyBean);

        findCompany(request,response);
    }

	
}
