package com.test.logistics.product.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.dao.DataAccessException;
import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.product.sf.ProductServiceFacade;
import com.test.logistics.product.sf.ProductServiceFacadeImpl;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.product.to.MrpTotalBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductMrpController  extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	ProductServiceFacade productServiceFacade = ProductServiceFacadeImpl.getInstance();
	HashMap<String, Object> map = new HashMap<String, Object>();
	ModelAndView modelAndView;




	public ModelAndView mrpOpenOut(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut-시작");
		}

		String lineNo = request.getParameter("code");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
	
		try {

			List<MrpBean> mrpBean=productServiceFacade.mrpOpenOut(lineNo, startDate, endDate);
			map.put("list", mrpBean);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
		
			if (logger.isDebugEnabled()) { logger.debug("MrpOpenController - end");}
			return null;

		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("mrpOpenOut-에러");
			}
			map.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/product/mrpForm", map);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut-끝");
		}
		return modelAndView;
	}
	
	public ModelAndView dateMrpList(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("dateMrpList-시작");
		}
			
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		try {
			
			List<MrpBean> mrpBean=productServiceFacade.dateMrp(startDate, endDate);
			map.put("list", mrpBean);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
			
			if (logger.isDebugEnabled()) { logger.debug("MrpOpenController - end");}
			return null;
			
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("mrpOpenOut-에러");
			}
			map.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/product/mrpForm", map);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("mrpOpenOut-끝");
		}
		return modelAndView;
	}

	public ModelAndView getMrpGathering(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("getTotalMrp-시작");
		}
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
	
		map.put("page",pagenum);
		map.put("total",pagecount);
		map.put("recoders","15");
		map.put("list",list);

		JSONObject jsonObject=JSONObject.fromObject(map);
		response.getWriter().println(jsonObject);
	
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getTotalMrp-에러");
			}
			map.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/product/mrpForm", map);
			return modelAndView;
		}

		if (logger.isDebugEnabled()) { logger.debug("MrpGatheringController - end");}
		return null;
	}

	
	public ModelAndView regisGatheringMrp(HttpServletRequest request,
			HttpServletResponse response) {
		List<MrpBean> bean=new ArrayList<MrpBean>();
		if (logger.isDebugEnabled()) { logger.debug("MrpGatheringRegisController - start");}
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpGatheringBeanList");
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				bean.add(mrpBean);
			}
			productServiceFacade.batchMrpGatheringProcess(bean);
			
			if (logger.isDebugEnabled()) { logger.debug("MrpGatheringRegisController - end");}
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}
	public ModelAndView findMrpCodeList(HttpServletRequest request, HttpServletResponse response){
		List<MrpBean> bean=new ArrayList<MrpBean>();
		if (logger.isDebugEnabled()) { logger.debug("findMrpCodeList-시작");}
		HashMap<String, Object> map = new HashMap<String, Object>();
		try{
			
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("lists"));
		
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpBeanList");
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				bean.add(mrpBean);
			}
			List<MrpBean> mrpCodeBean=productServiceFacade.getMrpCodeList(bean);
			map.put("list", mrpCodeBean);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
			return null;
		}
		catch (Exception e) {
			if (logger.isFatalEnabled()) { logger.fatal("findMrpCodeList - 에러");}
			map.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/product/mrpForm", map);
		}
		if (logger.isDebugEnabled()) { logger.debug("findMrpCodeList - 끝");}
		return modelAndView;	
	}
	
	public ModelAndView findMrpTotalReviewList(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
		JSONObject json = new JSONObject();
		PrintWriter out = null;

		try {
			out = response.getWriter();

			int pagenum = 1;
			int rowsize = 5;
			if (request.getParameter("page") != null) {
				pagenum = Integer.parseInt(request.getParameter("page"));
				rowsize = Integer.parseInt(request.getParameter("rows"));
			}
			int mrpTotalReviewCount = productServiceFacade.findMrpTotalReviewCount();
			System.out.println("@@@@@@@@@@@@"+mrpTotalReviewCount);
			ListForm page = new ListForm(rowsize, pagenum, mrpTotalReviewCount);
			List<MrpTotalBean> mrpTotalReviewList = productServiceFacade.findMrpTotalReviewList(page.getStartrow(),	page.getEndrow());
			page.setList(mrpTotalReviewList);

			json.put("mrpTotalReviewList", page);
			json.put("errorCode", 1);
			json.put("errorMsg", "-1");
		} catch (IOException ioe) {
			json.put("errorCode", -1);
			json.put("errorMsg", "-1");
			ioe.printStackTrace();
		} catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			json.put("errorCode", -2);
			json.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}
		System.out.println(json);
		out.print(json);
		out.close();

		return null;
	}
	
}