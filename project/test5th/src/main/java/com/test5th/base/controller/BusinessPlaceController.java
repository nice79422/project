package com.test5th.base.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.BusinessPlaceBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;


@Controller
public class BusinessPlaceController {
	/* baseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
    /* 사업장관리목록을 조회하는 메서드 */
	@RequestMapping("base/findBusinessPlaceList.do")
  	public void findBusinessPlaceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		
  		PlatformData outData = (PlatformData) request.getAttribute("outData");
  		List<BusinessPlaceBean> businessPlaceList=baseServiceFacade.findBusinessPlaceList();
          for(BusinessPlaceBean businessPlaceBean:businessPlaceList) {
          System.out.println("사업장코드:"+businessPlaceBean.getBusinessPlaceName());
          }
          datasetBeanMapper.beansToDataset(outData, businessPlaceList, BusinessPlaceBean.class);
          /*
           * 	beansToDataset : bean --> dataset
           * */
      }
  	
  	/*사업장관리 (등록/삭제/수정)*/
	@RequestMapping("base/batchBusinessPlaceList.do")
  	public void batchBusinessPlaceList(HttpServletRequest request, HttpServletResponse response) throws Exception {
  		/*
  		 * datasetToBean도 있다.
  		 *
  		 *  datasetToBeans : dataset --> bean
  		 *
  		 * */
  		  PlatformData inData = (PlatformData) request.getAttribute("inData");
          List<BusinessPlaceBean> businessPlaceList=datasetBeanMapper.datasetToBeans(inData, BusinessPlaceBean.class);
          baseServiceFacade.batchBusinessPlaceList(businessPlaceList);

          findBusinessPlaceList(request,response);
      }

	
}
