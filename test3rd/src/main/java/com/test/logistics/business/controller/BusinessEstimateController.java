package com.test.logistics.business.controller;


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
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;

import net.sf.json.JSONObject;

public class BusinessEstimateController extends MultiActionController {
	
	String business=null;
	BusinessServiceFacade businessServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	
	public final void setBusinessServiceFacade(BusinessServiceFacade businessServiceFacade) {
		this.businessServiceFacade = businessServiceFacade;
	}

	
	
	public ModelAndView findEstimate(HttpServletRequest request, HttpServletResponse response){
		
				
		try{
			List<EstimateBean> estimateList=businessServiceFacade.findEstimate();
			modelMap.put("emptyEstimate", new EstimateBean());
			modelMap.put("emptyEstimateItem", new EstimateDetailBean());
			modelMap.put("estimateList", estimateList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��");
		}catch(Exception ioe){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��?��");
			
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	
	

	public ModelAndView registerEstimate(HttpServletRequest request, HttpServletResponse response){
		
		try{
			String str=request.getParameter("estimateBeanList");
			
			ObjectMapper mapper = new ObjectMapper();
			List<EstimateBean> estimateList = mapper.readValue(str, new TypeReference<List<EstimateBean>>(){});
			
			businessServiceFacade.registerEstimate(estimateList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��곤옙");
			
		}catch(Exception e){
			e.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��곤옙 ?��?��?��?��?��?��?��?��?��?��?��.");
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	

	public ModelAndView getEstimateDetailList(HttpServletRequest request,HttpServletResponse response){
		
		String searchCode=request.getParameter("searchCode");
		try{
			business="estimateDetailList";
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=businessServiceFacade.getRowCount(business, searchCode);

			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<EstimateDetailBean> list=businessServiceFacade.findEstimateDetailList(sr, er, searchCode);
			int pagecount=listForm.getPagecount();
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",list);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��?��?��?��?��곤옙");
		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "?��?��?��리占?��?��?�� ?��?��?��?�� ?��?��?��?��?��?��?��?��?��?��?��.");
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}


	public ModelAndView modifyEstimate(HttpServletRequest request,HttpServletResponse response) {
		
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			EstimateBean estimateBean=(EstimateBean)JSONObject.toBean(jsonObject,EstimateBean.class);
			businessServiceFacade.estimateModify(estimateBean);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
		}catch(Exception e){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "?��?��?��?��?��?��?��?��?��?���? ?��?��?��?��?��?��?��?��?��?��?��.");
			modelMap.put("empBean", new EstimateBean());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView standByEstimate(HttpServletRequest request,	HttpServletResponse response) {
			
		
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			EstimateBean estimateBean=(EstimateBean)JSONObject.toBean(jsonObject,EstimateBean.class);
			businessServiceFacade.standByModify(estimateBean);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
		}catch(Exception e){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "?��?��?��?��?��?��처占?��?�� ?��?��?��?��?��?��?��?��?��?��?��.");
			modelMap.put("empBean", new EstimateBean());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView findEstimateReviewList(HttpServletRequest request, HttpServletResponse response) {
				
		try {
		
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			System.out.println("startDate : " + startDate + ", endDate : " + endDate);

			List<EstimateItemBean> estimateItemList = businessServiceFacade.findEstimateReviewList(startDate, endDate);
			modelMap.put("estimateItemList", estimateItemList);
			modelMap.put("errorCode", 1);
			modelMap.put("errorMsg", "견적리스?��조회 ?���?");
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

}
