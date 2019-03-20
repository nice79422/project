package com.test.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.base.to.ZipCodeBean;
import com.test.common.sf.CommonServiceFacade;

public class ZipCodeController extends MultiActionController {
	CommonServiceFacade commonServiceFacade; 


	public void setCommonServiceFacade(CommonServiceFacade commonServiceFacade) {
		this.commonServiceFacade = commonServiceFacade;
	}

	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView;

	public ModelAndView searchDong(HttpServletRequest request, HttpServletResponse response)  {
	      // TODO Auto-generated method stub
		
	      response.setContentType("text/json; charset=UTF-8");
	      String dong=request.getParameter("dong");
	      try {
	    	  List<ZipCodeBean> zipcodebean=commonServiceFacade.getZipcodeList(dong);
	      	  modelMap.put("zipcodebean",zipcodebean);
	   	} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "�ּ� ��ȸ �����Դϴ�.");
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
   }
}