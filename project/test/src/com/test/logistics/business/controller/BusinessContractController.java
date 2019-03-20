package com.test.logistics.business.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.dao.DataAccessException;
import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.sf.BusinessServiceFacadeImpl;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.business.to.ContractItemBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

import net.sf.json.JSONObject;

public class BusinessContractController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	String business=null;
	BusinessServiceFacade businessServiceFacade=BusinessServiceFacadeImpl.getInstance();
	ModelAndView modelAndView;
	HashMap<String,Object> map=new HashMap<String,Object>();


	public ModelAndView findContractList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findContractList-시작");}
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
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);

		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findContractList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/contractListForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findContractList-끝");}
		return modelAndView;
	}


	public ModelAndView registerContract(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("registerContract-시작");}
		try{
			String str=request.getParameter("contractBeanList");
			
			ObjectMapper mapper = new ObjectMapper();
			List<ContractBean> contractList = mapper.readValue(str, new TypeReference<List<ContractBean>>(){});
			

			businessServiceFacade.registerContract(contractList);

		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerContract-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/contractListForm", map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("registerContract-끝");}
		return modelAndView;
	}
	 
	
	public ModelAndView findContractDetailList(HttpServletRequest request,
			HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findContractDetailList-시작");}
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
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			
			response.getWriter().println(jsonObject);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findContractDetailList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/contractListForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findContractDetailList-끝");}
		return modelAndView;
	}
	
	public ModelAndView standByContract(HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) { logger.debug("standByConstract - start");}
		
		
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			ContractBean contractBean=(ContractBean)JSONObject.toBean(jsonObject,ContractBean.class);
			businessServiceFacade.standByContractModify(contractBean);
			System.out.println("@@@@@@@@"+contractBean);
			if (logger.isDebugEnabled()) { logger.debug("standByConstract - end"); }
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}
	
	public ModelAndView findContractReviewList(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("START");
		}
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
			json.put("errorMsg", "수주조회성공");
		} catch (IOException ioe) {
			json.put("errorCode", -1);
			json.put("errorMsg", "수주조회에러");
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
