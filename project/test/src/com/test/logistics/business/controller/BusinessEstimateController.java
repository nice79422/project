package com.test.logistics.business.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.common.exception.DataAccessException;
import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.sf.BusinessServiceFacadeImpl;
import com.test.logistics.business.to.ContractBean;
import com.test.logistics.business.to.EstimateBean;
import com.test.logistics.business.to.EstimateDetailBean;
import com.test.logistics.business.to.EstimateItemBean;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

public class BusinessEstimateController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	String business=null;
	BusinessServiceFacade businessServiceFacade=BusinessServiceFacadeImpl.getInstance();
	ModelAndView modelAndView;
	HashMap<String,Object> map=new HashMap<String,Object>();

	public ModelAndView findEstimateListal(HttpServletRequest request, HttpServletResponse response){  //수주에서 견적수주추가시 상제주문리스트 
		if (logger.isDebugEnabled()) { logger.debug("findEstimateListal-시작");}
		try{
			business="estimate";
			
			String searchCode=request.getParameter("searchCode");
			System.out.println("searchCode"+searchCode);
			
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=businessServiceFacade.getRowCount(business);			
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<EstimateDetailBean>  list=businessServiceFacade.findEstimateDetailListal(sr, er, searchCode);  //수주에서 견적수주 추가			   
			int pagecount=listForm.getPagecount();
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);
			modelAndView=null;
			System.out.println("다 받아온 json"+jsonObject);
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findEstimateList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-끝");}
		return modelAndView;
	}
	
	public ModelAndView findEstimateList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-시작");}
		try{
			business="estimate";
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=businessServiceFacade.getRowCount(business);
			
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<EstimateBean> list=businessServiceFacade.findEstimateList(sr, er);
			
			int pagecount=listForm.getPagecount();
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);
			
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findEstimateList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-끝");}
		return modelAndView;
	}
	
	public ModelAndView findEstimate(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findEstimate-시작");}
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<EstimateBean> estimateList=businessServiceFacade.findEstimate();
			json.put("emptyEstimate", new EstimateBean());
			json.put("emptyEstimateItem", new EstimateDetailBean());
			json.put("estimateList", estimateList);
			json.put("errorCode", 1);
			json.put("errorMsg", "견적서조회성공");
		}catch(IOException ioe){
			json.put("errorCode", -1);
			json.put("errorMsg", "견적서조회오류");
			ioe.printStackTrace();
		}
		catch (DataAccessException e) {
			logger.fatal(e.getMessage());
			json.put("errorCode", -2);
			json.put("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("END");
		}
		
		out.print(json);
		out.close();

		return null;
	}


	public ModelAndView registerEstimate(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("registerEstimate-시작");}
		try{
			String str=request.getParameter("estimateBeanList");
			
			ObjectMapper mapper = new ObjectMapper();
			List<EstimateBean> estimateList = mapper.readValue(str, new TypeReference<List<EstimateBean>>(){});
			

			businessServiceFacade.registerEstimate(estimateList);

			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerEstimate-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm", map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("registerEstimate-끝");}
		return modelAndView;
	}
	
	public ModelAndView registerContract(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("registerContract-시작");}
		try{
			String str=request.getParameter("contractBeanList");
		
			ObjectMapper mapper = new ObjectMapper();
			List<ContractBean> contractList = mapper.readValue(str, new TypeReference<List<ContractBean>>(){});
			

			businessServiceFacade.registerContract(contractList);

			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerContract-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm", map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("registerContract-끝");}
		return modelAndView;
	}


	public ModelAndView getEstimateDetailList(HttpServletRequest request,
			HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("getEstimateDetailList-시작");}
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
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
		
			response.getWriter().println(jsonObject);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("getEstimateDetailList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("getEstimateDetailList-끝");}
		return modelAndView;
	}

	public ModelAndView modifyEstimate(HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) { logger.debug("modifyEstimate - start");}
	

		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			EstimateBean estimateBean=(EstimateBean)JSONObject.toBean(jsonObject,EstimateBean.class);
			businessServiceFacade.estimateModify(estimateBean);
			if (logger.isDebugEnabled()) { logger.debug("modifyEstimate - end"); }
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}
	public ModelAndView standByEstimate(HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) { logger.debug("standByEstimate - start");}
		
		
		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("bean"));
			EstimateBean estimateBean=(EstimateBean)JSONObject.toBean(jsonObject,EstimateBean.class);
			businessServiceFacade.standByModify(estimateBean);
			if (logger.isDebugEnabled()) { logger.debug("standByEstimate - end"); }
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}

	public ModelAndView findEstimateReviewList(HttpServletRequest request, HttpServletResponse response) {
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

			List<EstimateItemBean> estimateItemList = businessServiceFacade.findEstimateReviewList(startDate, endDate);
			json.put("estimateItemList", estimateItemList);
			json.put("errorCode", 1);
			json.put("errorMsg", "견적서조회성공");
		} catch (IOException e) {
			json.put("errorCode", -1);
			json.put("errorMsg", "견적서조회에러");
			e.printStackTrace();
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

	public ModelAndView searchEstimateDetailInfo(HttpServletRequest request, HttpServletResponse response) {

	      if (logger.isDebugEnabled()) {
	         logger.debug("EstimateController : searchEstimateDetailInfo 시작");
	      }

	      String estimateNo = request.getParameter("estimateNo");

	      JSONObject jsonObject = null;
	      HashMap<String, Object> map = new HashMap<>();
	  	  
	      PrintWriter out = null;
	      

	      try {
	         out = response.getWriter();

	         ArrayList<EstimateDetailBean> estimateDetailTOList = businessServiceFacade.getEstimateDetailList(estimateNo);
	         
	         
	         map.put("gridRowJson", estimateDetailTOList);
	         map.put("errorCode", 1);
	         map.put("errorMsg", "성공");
	         jsonObject=JSONObject.fromObject(map);

	      } catch (IOException e1) {
	         e1.printStackTrace();
	         map.put("errorCode", -1);
	         map.put("errorMsg", e1.getMessage());

	      } catch (DataAccessException e2) {
	         e2.printStackTrace();
	         map.put("errorCode", -2);
	         map.put("errorMsg", e2.getMessage());

	      } finally {
	         out.println(jsonObject);
	         out.close();
	      }

	      if (logger.isDebugEnabled()) {
	         logger.debug("EstimateController : searchEstimateDetailInfo 종료");
	      }
	      return null;
	   }
	
	
}
