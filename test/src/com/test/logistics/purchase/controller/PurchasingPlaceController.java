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
				logger.debug("findPurchasingPlaceList - 시작");
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
				logger.fatal("findPurchasingPlaceList - 에러");
			}
			modelObject.put("errormsg", e.getMessage());
			modelAndView = new ModelAndView("logistics/purchase/purchasingPlaceListForm", modelObject);
			if (logger.isDebugEnabled()) {
				logger.debug("findPurchasingPlaceList - 끝 ");
			}

		}
		return modelAndView;
	}

	public ModelAndView getEmptyPurchasingPlace(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			if (logger.isDebugEnabled()) {
				logger.debug("getEmptyPurchasingPlace - 시작");
			}
			modelObject.put("emptyPurchasingPlaceBean", new PurchasingPlaceBean()); // 빈객체를 날려준다
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			response.getWriter().println(json);
			modelAndView = null;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("getEmptyPurchasingPlace - 에러");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "거래처 등록 오류입니다");
			JSONObject json = JSONObject.fromObject(modelObject);
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			modelAndView = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("getEmptyPurchasingPlace - 끝");
		}
		return modelAndView;
	}

	public ModelAndView registerPurchasingPlace(HttpServletRequest request,
			HttpServletResponse response){
		if(logger.isDebugEnabled()){
			   logger.debug("registerPurchasingPlace - 시작");
		   }
		try{
			request.setCharacterEncoding("UTF-8");
			String purchasingPlaceBean=request.getParameter("PurchasingPlaceBean");
		
			JSONObject jsonObject=JSONObject.fromObject(purchasingPlaceBean);
			PurchasingPlaceBean purchasingPlace=(PurchasingPlaceBean)JSONObject.toBean(jsonObject,PurchasingPlaceBean.class);
	     
	        purchaseServiceFacade.registerPurchasingPlace(purchasingPlace);
			modelObject.put("customerBean", purchasingPlace);
			 if(logger.isDebugEnabled()){
		    	  logger.debug("registerPurchasingPlace - 끝 ");
		      }

		}catch(IOException e){
			 if(logger.isFatalEnabled()){
	    		 logger.fatal("registerPurchasingPlace - 에러");
	    	 }
			modelObject.clear();
		    modelObject.put("errorCode", -1);
		    modelObject.put("errorMsg", "거래처등록 오류입니다.");
		    JSONObject json = JSONObject.fromObject(modelObject);
		    modelObject.put("emptyPurchasingPlaceBean", new PurchasingPlaceBean());
		    modelObject.put("errorCode", 0);
		    modelObject.put("errorMsg", "Success!");
		    try {
		     response.getWriter().println(json);
		    } catch (IOException e1) {
		    	 if(logger.isFatalEnabled()){
		    		 logger.fatal("registerPurchasingPlace - 에러");
		    	 }
		    	 e1.printStackTrace();
		    }
		   }
		   if(logger.isDebugEnabled()){
			   logger.debug("PurchasingPlaceRegisterController - 끝");
		   }
		   return null;
		 }
}