package com.test.logistics.item.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.sf.ItemServiceFacadeImpl;
import com.test.logistics.item.to.StockBean;

import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StockController extends MultiActionController {
	 protected final Log logger = LogFactory.getLog(getClass());
	ItemServiceFacade itemServiceFacade=ItemServiceFacadeImpl.getInstance();
	   HashMap<String,Object> modelObject=new HashMap<String,Object>();
	   ModelAndView modelAndView;


	

	public ModelAndView findDateStockList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){
			   logger.debug("findDateStockList - ����");
		   }
		try{
			response.setContentType("text/json; charset=UTF-8");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			List<StockBean> list=itemServiceFacade.getDateStockList(start, end);
			modelObject.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(modelObject);
				response.getWriter().println(jsonObject);
			if(logger.isDebugEnabled()){
			    logger.debug("findDateStockList - �� ");
			}
			return null;
			}catch(Exception e){
				if(logger.isFatalEnabled()){
					 logger.fatal("findDateStockList - ����");
				 }
				modelObject.put("errormsg", e.getMessage());
				modelAndView=new ModelAndView("/logistics/item/stockListform",modelObject);
				return modelAndView;
			}
	}


	public ModelAndView findItemStockList(HttpServletRequest request, HttpServletResponse response){
		 if(logger.isDebugEnabled()){
			   logger.debug("findItemStockList - ����");
		   }
		try{
			response.setContentType("text/json; charset=UTF-8");
			String item = request.getParameter("item");
			List<StockBean> list=itemServiceFacade.getItemStockList(item);
			modelObject.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(modelObject);
				response.getWriter().println(jsonObject);
				
			if(logger.isDebugEnabled()){
				 logger.debug("findItemStockList - �� ");
			}
			return null;
			}catch(Exception e){
				if(logger.isFatalEnabled()){
					 logger.fatal("findItemStockList - ����");
				 }
				modelObject.put("errormsg", e.getMessage());
				modelAndView=new ModelAndView("/logistics/item/stockListform",modelObject);
				return modelAndView;

			}
	}
	
	public ModelAndView modifyStock(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyStock - ����");
		}
		List<StockBean> doAddStock = new ArrayList<StockBean>();
		try {
			
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			System.out.println("StockLIST:"+list);
			JSONObject jsonObject = JSONObject.fromObject(list);
			System.out.println("jsonObject:"+jsonObject);
			JSONArray jsonStockBean = jsonObject.getJSONArray("addStockList");
			System.out.println("jsonStockBean:"+jsonStockBean);
			for (int index = 0; index < jsonStockBean.size(); index++) {
				StockBean stockBean = (StockBean) JSONObject.toBean(jsonStockBean.getJSONObject(index), StockBean.class);
				doAddStock.add(stockBean);
			}
			
			itemServiceFacade.modifyStock(doAddStock);
		} catch (IOException e) {
			
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyStock - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "STOCK��� �����Դϴ�.");
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("stockBean", new StockBean());
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("modifyStock - �� ");
		}
		return modelAndView;
	}
}
