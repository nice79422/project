package com.test.logistics.product.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.common.to.ListForm;
import com.test.logistics.product.sf.ProductServiceFacade;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductMrpController  extends MultiActionController {
	ProductServiceFacade productServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	public void setProductServiceFacade(ProductServiceFacade productServiceFacade) {
		this.productServiceFacade = productServiceFacade;
	}
	


	public ModelAndView mrpOpenOut(HttpServletRequest request, HttpServletResponse response) {

		String lineNo = request.getParameter("code");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
	
		try {

			List<MrpBean> mrpBean=productServiceFacade.mrpOpenOut(lineNo, startDate, endDate);
			modelMap.put("list", mrpBean);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
		

		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	
	public ModelAndView dateMrpList(HttpServletRequest request, HttpServletResponse response) {
			
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		try {
			
			List<MrpBean> mrpBean=productServiceFacade.dateMrp(startDate, endDate);
			modelMap.put("list", mrpBean);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
			
			
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView getMrpGathering(HttpServletRequest request, HttpServletResponse response) {
		String supply = request.getParameter("supply");
		try{
		int pagenum=1;
		if(request.getParameter("page")!=null)
			pagenum=Integer.parseInt(request.getParameter("page"));
		int rowsize=100;
		if(request.getParameter("rows")!=null)
			rowsize=Integer.parseInt(request.getParameter("rows"));
		int dbcount=productServiceFacade.getTotalMrpRowCount(supply);
	
		ListForm listForm=new ListForm();
		listForm.setRowsize(rowsize);
		listForm.setPagenum(pagenum);
		listForm.setDbcount(dbcount);
		int sr=listForm.getStartrow();
		int er=listForm.getEndrow();
		int pagecount=listForm.getPagecount();
		List<MrpBean> list=productServiceFacade.getMrpGathering(sr,er,supply);
	
		modelMap.put("page",pagenum);
		modelMap.put("total",pagecount);
		modelMap.put("recoders","15");
		modelMap.put("list",list);
		modelMap.put("errorCode", 0); 
		modelMap.put("errorMsg", "success");
	
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	
	public ModelAndView regisGatheringMrp(HttpServletRequest request,HttpServletResponse response) {
		List<MrpBean> bean=new ArrayList<MrpBean>();

		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));//json????????????????ï¿?? ï§?ï¿½ï¿½ï¿½ä»¥?ï¿½ï¿½ list
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpGatheringBeanList");
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				bean.add(mrpBean);
			}
			productServiceFacade.batchMrpGatheringProcess(bean);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
			
		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	
	public ModelAndView findMrpCodeList(HttpServletRequest request, HttpServletResponse response){
		List<MrpBean> bean=new ArrayList<MrpBean>();

		try{
			
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("lists"));
		
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpBeanList");
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				bean.add(mrpBean);
			}
			List<MrpBean> mrpCodeBean=productServiceFacade.getMrpCodeList(bean);
			modelMap.put("list", mrpCodeBean);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "success");
		}
		catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;	
	}
	
	public ModelAndView findMrpTotalReviewList(HttpServletRequest request, HttpServletResponse response) {
	

		try {
			int pagenum = 1;
			int rowsize = 5;
			if (request.getParameter("page") != null) {
				pagenum = Integer.parseInt(request.getParameter("page"));
				rowsize = Integer.parseInt(request.getParameter("rows"));
			}
			int mrpTotalReviewCount = productServiceFacade.findMrpTotalReviewCount();
			
			ListForm page = new ListForm(rowsize, pagenum, mrpTotalReviewCount);
			List<MrpTotalBean> mrpTotalReviewList = productServiceFacade.findMrpTotalReviewList(page.getStartrow(),	page.getEndrow());
			page.setList(mrpTotalReviewList);

			modelMap.put("mrpTotalReviewList", page);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());

		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;	
	}
	
}