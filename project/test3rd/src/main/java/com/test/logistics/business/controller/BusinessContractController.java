package com.test.logistics.business.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.exception.DataAccessException;
import com.test.common.to.ListForm;
import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;

import net.sf.json.JSONObject;

public class BusinessContractController extends MultiActionController {
	
	String business=null;
	BusinessServiceFacade businessServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();



	public final void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}


	public ModelAndView findContractList(HttpServletRequest request, HttpServletResponse response){
		
		try{
			business="contract";
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=businessServiceFacade.getRowCount(business);
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<ContractBean> list=businessServiceFacade.findContractList(sr, er);
			int pagecount=listForm.getPagecount();
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",list);
			modelMap.put("errorCode",0);
			modelMap.put("errorMsg","success");
	

		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}


	public ModelAndView registerContract(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String str=request.getParameter("contractBeanList");
			
			ObjectMapper mapper = new ObjectMapper();
			List<ContractBean> contractList = mapper.readValue(str, new TypeReference<List<ContractBean>>(){});
			

			businessServiceFacade.registerContract(contractList);
			modelMap.put("errorCode", 0); // ?��?��처리?�� ?��?�� 0?? ?���?
			modelMap.put("errorMsg", "success");
		}catch(Exception e){
			e.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "코드리스?�� 조회 ?��류입?��?��.");
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}
	 
	
	public ModelAndView findContractDetailList(HttpServletRequest request,HttpServletResponse response){
	
		String searchCode=request.getParameter("searchCode");
		
		try{
			business="contractDetailList";
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=businessServiceFacade.getRowCount(business, searchCode);
			
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<ContractDetailBean> list=businessServiceFacade.findContractDetailList(sr, er, searchCode);
			
			int pagecount=listForm.getPagecount();
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",list);
			modelMap.put("errorCode", 0); // ?��?��처리?�� ?��?�� 0?? ?���?
			modelMap.put("errorMsg", "success");
			
		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
			
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}

	
	public ModelAndView standByContract(HttpServletRequest request,	HttpServletResponse response) {
				
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			ContractBean contractBean=(ContractBean)JSONObject.toBean(jsonObject,ContractBean.class);
			businessServiceFacade.standByContractModify(contractBean);
			return null;
		}catch(Exception e){
			modelMap.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",modelMap);
			return modelAndView;
		}
	}
	
	public ModelAndView findContractReviewList(HttpServletRequest request, HttpServletResponse response) {
		
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try {
			out = response.getWriter();

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			System.out.println("startDate : " + startDate + ", endDate : " + endDate);

			List<ContractItemBean> contractItemList = businessServiceFacade.findContractReviewList(startDate, endDate);
			json.put("contractItemList", contractItemList);
			json.put("errorCode", 1);
			json.put("errorMsg", "?��?����?�����??��?��?���わ?����?��곌�?��");
		} catch (IOException ioe) {
			json.put("errorCode", -1);
			json.put("errorMsg", "?��?����?�����??��?��?���わ?����?��곌�?�띰?����?��?����");
			ioe.printStackTrace();
		} catch (DataAccessException e) {
			
			json.put("errorCode", -2);
			json.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
	
		System.out.println(json);
		out.print(json);
		out.close();
		return null;
	}
}
