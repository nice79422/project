package com.test.logistics.purchase.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.product.sf.ProductServiceFacade;
import com.test.logistics.product.sf.ProductServiceFacadeImpl;
import com.test.logistics.product.to.MrpBean;
import com.test.logistics.purchase.sf.PurchaseServiceFacade;
import com.test.logistics.purchase.sf.PurchaseServiceFacadeImpl;
import com.test.logistics.purchase.to.PurchaseBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductPurchaseController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	PurchaseServiceFacade purchaseServiceFacade = PurchaseServiceFacadeImpl.getInstance();
	ProductServiceFacade productServiceFacade = ProductServiceFacadeImpl.getInstance();
	ModelAndView modelAndView;
	HashMap<String, Object> map = new HashMap<String, Object>();



	public ModelAndView getPurchaseList(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("PurchaseOrderListController - start");
		}
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
			map.put("page", pagenum);
			map.put("total", pagecount);
			map.put("list", mrpBean);
			map.put("mrpBean", new MrpBean());
			map.put("errorCode", 0);
			map.put("errorMsg", "success");
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
			
			if (logger.isDebugEnabled()) {
				logger.debug("PurchaseOrderListController - end");
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			map.clear();
			map.put("errorCode", -1);
			map.put("errorMsg", "Purchase Order 조회 오류입니다.");
			JSONObject json = JSONObject.fromObject(map);
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			modelAndView = null;
		}
		return modelAndView;
	}

	public ModelAndView regisPurchase(HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) { logger.debug("PurchaseRegisController - start");}
		List<MrpBean> bean=new ArrayList<MrpBean>();

		try{
			
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));
			
			JSONArray jsonMrpBeanList=jsonObject.getJSONArray("mrpBeanList");
			
			for(int index=0; index<jsonMrpBeanList.size(); index++){
				MrpBean mrpBean=(MrpBean)JSONObject.toBean(jsonMrpBeanList.getJSONObject(index),MrpBean.class);
				
				bean.add(mrpBean);
			}
			
			purchaseServiceFacade.purchaseCrud(bean);
			if (logger.isDebugEnabled()) { logger.debug("PurchaseRegisController - end"); }
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}

	public void findPurchaseList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("getPurchaseList-시작");}
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
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);

			JSONObject jsonObject=JSONObject.fromObject(map);
			
			response.getWriter().println(jsonObject);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("getPurchaseList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/purchase/purchaseform",map);
		}
		if (logger.isDebugEnabled()) { logger.debug("getPurchaseList-끝");}
	}

	
	
	
	
	
}