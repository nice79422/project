package com.test.logistics.business.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.common.to.ListForm;
import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.to.ClientBean;

import net.sf.json.JSONObject;

public class ClientController extends MultiActionController {
	
	BusinessServiceFacade businessServiceFacade;
	
	String business = null;
	ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	
	public void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

	public ModelAndView findClientList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			business = "customer";
			int dbcount = businessServiceFacade.getRowCount(business);
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<ClientBean> list = businessServiceFacade.clientList(sr, er);
			
			int pagecount = listForm.getPagecount();
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("recoders", "11");
			modelMap.put("list", list);

		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());

		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}

	
	public ModelAndView getEmptyClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
		
			modelMap.put("emptyClientBean", new ClientBean()); // ��ü�� �����ش�
			
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
			
			
		} catch (Exception e) {
			
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "�ŷ�ó ��� �����Դϴ�");
			
		
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}

	public ModelAndView registerClient(HttpServletRequest request,HttpServletResponse response){
		
		try{
			request.setCharacterEncoding("UTF-8");
			String clientBean=request.getParameter("ClientBean");
		
			JSONObject jsonObject=JSONObject.fromObject(clientBean);
			ClientBean client=(ClientBean)JSONObject.toBean(jsonObject,ClientBean.class);
	    
	        businessServiceFacade.registerClient(client);
			modelMap.put("customerBean", client);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");

		}catch(IOException e){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
			
		   }
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
		 }
}