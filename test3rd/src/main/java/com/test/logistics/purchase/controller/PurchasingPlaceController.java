package com.test.logistics.purchase.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.common.to.ListForm;
import com.test.logistics.purchase.sf.PurchaseServiceFacade;
import com.test.logistics.purchase.to.PurchasingPlaceBean;

import net.sf.json.JSONObject;

public class PurchasingPlaceController extends MultiActionController {
	ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	
	
	PurchaseServiceFacade purchaseServiceFacade;
	
	public void setPurchaseServiceFacade(PurchaseServiceFacade purchaseServiceFacade) {
		this.purchaseServiceFacade = purchaseServiceFacade;
	}


	public ModelAndView findPurchasingPlaceList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			
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
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("recoders", "11");
			modelMap.put("list", list);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "success");

		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
			}

		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView getEmptyPurchasingPlace(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			modelMap.put("emptyPurchasingPlaceBean", new PurchasingPlaceBean()); // ��ü�� �����ش�
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView registerPurchasingPlace(HttpServletRequest request,	HttpServletResponse response){
		try{
			request.setCharacterEncoding("UTF-8");
			String purchasingPlaceBean=request.getParameter("PurchasingPlaceBean");
		
			JSONObject jsonObject=JSONObject.fromObject(purchasingPlaceBean);
			PurchasingPlaceBean purchasingPlace=(PurchasingPlaceBean)JSONObject.toBean(jsonObject,PurchasingPlaceBean.class);
	     
	        purchaseServiceFacade.registerPurchasingPlace(purchasingPlace);
	    	modelMap.put("customerBean", purchasingPlace);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "success");

		}catch(IOException e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
						
		   }
			modelAndView = new ModelAndView("jsonView", modelMap);
			return modelAndView;
		   
		 }
}