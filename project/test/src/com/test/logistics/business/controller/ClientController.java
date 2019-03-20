package com.test.logistics.business.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.business.sf.BusinessServiceFacade;
import com.test.logistics.business.sf.BusinessServiceFacadeImpl;
import com.test.logistics.business.to.ClientBean;

import net.sf.json.JSONObject;

public class ClientController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	BusinessServiceFacade businessServiceFacade = BusinessServiceFacadeImpl.getInstance();
	HashMap<String, Object> modelObject = new HashMap<String, Object>();
	String business = null;
	ModelAndView modelAndView;


	public ModelAndView findClientList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("findClientList - ����");
			}
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
			modelObject.put("page", pagenum);
			modelObject.put("total", pagecount);
			modelObject.put("recoders", "11");
			modelObject.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(modelObject);
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findClientList - ����");
			}
			modelObject.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/business/clientListForm", modelObject);
			if (logger.isDebugEnabled()) {
				logger.debug("findClientList - �� ");
			}

		}
		return modelAndView;
	}

	public ModelAndView getEmptyClient(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getEmptyClient - ����");
			}
			modelObject.put("emptyClientBean", new ClientBean()); // ��ü�� �����ش�
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			response.getWriter().println(json);
			modelAndView = null;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getEmptyClient - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "�ŷ�ó ��� �����Դϴ�");
			JSONObject json = JSONObject.fromObject(modelObject);
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			modelAndView = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getEmptyClient - ��");
		}
		return modelAndView;
	}

	public ModelAndView registerClient(HttpServletRequest request,
			HttpServletResponse response){
		if(logger.isDebugEnabled()){
			   logger.debug("registerClient - ����");
		   }
		try{
			request.setCharacterEncoding("UTF-8");
			String clientBean=request.getParameter("ClientBean");
		
			JSONObject jsonObject=JSONObject.fromObject(clientBean);
			ClientBean client=(ClientBean)JSONObject.toBean(jsonObject,ClientBean.class);
	    
	        businessServiceFacade.registerClient(client);
			modelObject.put("customerBean", client);
			 if(logger.isDebugEnabled()){
		    	  logger.debug("registerClient - �� ");
		      }

		}catch(IOException e){
			 if(logger.isFatalEnabled()){
	    		 logger.fatal("registerClient - ����");
	    	 }
			modelObject.clear();
		    modelObject.put("errorCode", -1);
		    modelObject.put("errorMsg", "�ŷ�ó��� �����Դϴ�.");
		    JSONObject json = JSONObject.fromObject(modelObject);
		    modelObject.put("emptyClientBean", new ClientBean());
		    modelObject.put("errorCode", 0);
		    modelObject.put("errorMsg", "Success!");
		    try {
		     response.getWriter().println(json);
		    } catch (IOException e1) {
		    	 if(logger.isFatalEnabled()){
		    		 logger.fatal("registerClient - ����");
		    	 }
		    	 e1.printStackTrace();
		    }
		   }
		   if(logger.isDebugEnabled()){
			   logger.debug("ClientRegisterController - ��");
		   }
		   return null;
		 }
}