package com.test.logistics.purchase.controller;

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
import com.test.logistics.purchase.sf.PurchaseServiceFacade;
import com.test.logistics.purchase.sf.PurchaseServiceFacadeImpl;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

import net.sf.json.JSONObject;

public class PurchasingPlaceController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	PurchaseServiceFacade purchaseServiceFacade = PurchaseServiceFacadeImpl.getInstance();
	HashMap<String, Object> modelObject = new HashMap<String, Object>();
	
	ModelAndView modelAndView;



	public ModelAndView findPurchasingPlaceList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("findPurchasingPlaceList - ����");
			}
			
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			
			int dbcount = purchaseServiceFacade.getRowCount();
			
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<PurchasingPlaceBean> list = purchaseServiceFacade.purchasingPlaceList(sr, er);
			
			
			int pagecount = listForm.getPagecount();
			modelObject.put("page", pagenum);
			modelObject.put("total", pagecount);
			modelObject.put("recoders", "11");
			modelObject.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(modelObject);
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findPurchasingPlaceList - ����");
			}
			modelObject.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/purchase/purchasingPlaceListForm", modelObject);
			if (logger.isDebugEnabled()) {
				logger.debug("findPurchasingPlaceList - �� ");
			}

		}
		return modelAndView;
	}

	public ModelAndView getEmptyPurchasingPlace(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getEmptyPurchasingPlace - ����");
			}
			modelObject.put("emptyPurchasingPlaceBean", new PurchasingPlaceBean()); // ��ü�� �����ش�
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			response.getWriter().println(json);
			modelAndView = null;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getEmptyPurchasingPlace - ����");
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
			logger.debug("getEmptyPurchasingPlace - ��");
		}
		return modelAndView;
	}

	public ModelAndView registerPurchasingPlace(HttpServletRequest request,
			HttpServletResponse response){
		if(logger.isDebugEnabled()){
			   logger.debug("registerPurchasingPlace - ����");
		   }
		try{
			request.setCharacterEncoding("UTF-8");
			String purchasingPlaceBean=request.getParameter("PurchasingPlaceBean");
		
			JSONObject jsonObject=JSONObject.fromObject(purchasingPlaceBean);
			PurchasingPlaceBean purchasingPlace=(PurchasingPlaceBean)JSONObject.toBean(jsonObject,PurchasingPlaceBean.class);
	     
	        purchaseServiceFacade.registerPurchasingPlace(purchasingPlace);
			modelObject.put("customerBean", purchasingPlace);
			 if(logger.isDebugEnabled()){
		    	  logger.debug("registerPurchasingPlace - �� ");
		      }

		}catch(IOException e){
			 if(logger.isFatalEnabled()){
	    		 logger.fatal("registerPurchasingPlace - ����");
	    	 }
			modelObject.clear();
		    modelObject.put("errorCode", -1);
		    modelObject.put("errorMsg", "�ŷ�ó��� �����Դϴ�.");
		    JSONObject json = JSONObject.fromObject(modelObject);
		    modelObject.put("emptyPurchasingPlaceBean", new PurchasingPlaceBean());
		    modelObject.put("errorCode", 0);
		    modelObject.put("errorMsg", "Success!");
		    try {
		     response.getWriter().println(json);
		    } catch (IOException e1) {
		    	 if(logger.isFatalEnabled()){
		    		 logger.fatal("registerPurchasingPlace - ����");
		    	 }
		    	 e1.printStackTrace();
		    }
		   }
		   if(logger.isDebugEnabled()){
			   logger.debug("PurchasingPlaceRegisterController - ��");
		   }
		   return null;
		 }
}