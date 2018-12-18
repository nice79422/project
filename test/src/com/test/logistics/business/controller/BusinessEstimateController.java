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

	public ModelAndView findEstimateListal(HttpServletRequest request, HttpServletResponse response){  //���ֿ��� ���������߰��� �����ֹ�����Ʈ 
		if (logger.isDebugEnabled()) { logger.debug("findEstimateListal-����");}
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
			List<EstimateDetailBean>  list=businessServiceFacade.findEstimateDetailListal(sr, er, searchCode);  //���ֿ��� �������� �߰�			   
			int pagecount=listForm.getPagecount();
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);
			modelAndView=null;
			System.out.println("�� �޾ƿ� json"+jsonObject);
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findEstimateList-����");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-��");}
		return modelAndView;
	}
	
	public ModelAndView findEstimateList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-����");}
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
			if (logger.isFatalEnabled()) { logger.fatal("findEstimateList-����");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("findEstimateList-��");}
		return modelAndView;
	}
	
	public ModelAndView findEstimate(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findEstimate-����");}
		JSONObject json = new JSONObject();
		PrintWriter out = null;
		try{
			out = response.getWriter();
			List<EstimateBean> estimateList=businessServiceFacade.findEstimate();
			json.put("emptyEstimate", new EstimateBean());
			json.put("emptyEstimateItem", new EstimateDetailBean());
			json.put("estimateList", estimateList);
			json.put("errorCode", 1);
			json.put("errorMsg", "��������ȸ����");
		}catch(IOException ioe){
			json.put("errorCode", -1);
			json.put("errorMsg", "��������ȸ����");
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
		if (logger.isDebugEnabled()) { logger.debug("registerEstimate-����");}
		try{
			String str=request.getParameter("estimateBeanList");
			
			ObjectMapper mapper = new ObjectMapper();
			List<EstimateBean> estimateList = mapper.readValue(str, new TypeReference<List<EstimateBean>>(){});
			

			businessServiceFacade.registerEstimate(estimateList);

			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerEstimate-����");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm", map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("registerEstimate-��");}
		return modelAndView;
	}
	
	public ModelAndView registerContract(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("registerContract-����");}
		try{
			String str=request.getParameter("contractBeanList");
		
			ObjectMapper mapper = new ObjectMapper();
			List<ContractBean> contractList = mapper.readValue(str, new TypeReference<List<ContractBean>>(){});
			

			businessServiceFacade.registerContract(contractList);

			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("registerContract-����");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm", map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("registerContract-��");}
		return modelAndView;
	}


	public ModelAndView getEstimateDetailList(HttpServletRequest request,
			HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("getEstimateDetailList-����");}
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
			if (logger.isFatalEnabled()) { logger.fatal("getEstimateDetailList-����");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/business/estimateForm",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("getEstimateDetailList-��");}
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
			json.put("errorMsg", "��������ȸ����");
		} catch (IOException e) {
			json.put("errorCode", -1);
			json.put("errorMsg", "��������ȸ����");
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
	         logger.debug("EstimateController : searchEstimateDetailInfo ����");
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
	         map.put("errorMsg", "����");
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
	         logger.debug("EstimateController : searchEstimateDetailInfo ����");
	      }
	      return null;
	   }
	
	
}
