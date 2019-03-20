package com.test.logistics.purchase.controller;

import java.io.IOException;
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
import com.test.logistics.purchase.sf.PurchaseServiceFacade;
import com.test.logistics.purchase.to.PurchaseBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductPurchaseController extends MultiActionController {
	PurchaseServiceFacade purchaseServiceFacade;
	ProductServiceFacade productServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();


	public void setPurchaseServiceFacade(PurchaseServiceFacade purchaseServiceFacade) {
		this.purchaseServiceFacade = purchaseServiceFacade;
	}

	public void setProductServiceFacade(ProductServiceFacade productServiceFacade) {
		this.productServiceFacade = productServiceFacade;
	}
	

	public ModelAndView getPurchaseList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			int pagenum = 1;
			if (request.getParameter("page") != null) {
				pagenum = Integer.parseInt(request.getParameter("page"));
			}
			int rowsize = 1;
			if (request.getParameter("rows") != null) {
				rowsize = Integer.parseInt(request.getParameter("rows"));
			}
			int dbcount = purchaseServiceFacade.getPurchaseOrderRowCount(code); //
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<MrpBean> mrpBean = purchaseServiceFacade.findPurchaseOrder(sr, er, code);
			int pagecount = listForm.getPagecount();
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("list", mrpBean);
			modelMap.put("mrpBean", new MrpBean());
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "success");

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "Purchase Order ��ȸ �����Դϴ�.");
			}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
		}


	public ModelAndView regisPurchase(HttpServletRequest request,HttpServletResponse response) {
		List<MrpBean> bean=new ArrayList<MrpBean>();

		try{
			
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));
			
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpBeanList");
			
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				
				bean.add(mrpBean);
			}
			
			purchaseServiceFacade.purchaseCrud(bean);
			modelMap.put("errormsg", 0);
			modelMap.put("errormsg", "success");
		}catch(Exception e){
			modelMap.put("errormsg", -1);
			modelMap.put("errormsg",  e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView findPurchaseList(HttpServletRequest request, HttpServletResponse response){
		try{
			
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			int dbcount=purchaseServiceFacade.getPurchaseRowCount();
			
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			
			
		   List<PurchaseBean> list=purchaseServiceFacade.findPurchaseList(sr, er);
			
		    int pagecount=listForm.getPagecount();
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",list);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "success");

		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;	
	
	}
}