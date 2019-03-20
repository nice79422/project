package com.test.logistics.item.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.to.StockBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StockController extends MultiActionController {
	
	ItemServiceFacade itemServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	public void setItemServiceFacade(ItemServiceFacade itemServiceFacade) {
		this.itemServiceFacade = itemServiceFacade;
	}
	

	public ModelAndView findDateStockList(HttpServletRequest request, HttpServletResponse response){
		
		try{
			response.setContentType("text/json; charset=UTF-8");
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			List<StockBean> list=itemServiceFacade.getDateStockList(start, end);
			modelMap.put("list", list);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
			
			}catch(Exception e){
				modelMap.put("errorCode", -1);
				modelMap.put("errormsg", e.getMessage());
			}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}


	public ModelAndView findItemStockList(HttpServletRequest request, HttpServletResponse response){
		try{
			response.setContentType("text/json; charset=UTF-8");
			String item = request.getParameter("item");
			List<StockBean> list=itemServiceFacade.getItemStockList(item);
			modelMap.put("list", list);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
			System.out.println("@@@@@@@@"+modelMap);
			}catch(Exception e){
			e.printStackTrace();
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
			}
			modelAndView = new ModelAndView("jsonView", modelMap);
			
			return modelAndView;
	}
	
	public ModelAndView modifyStock(HttpServletRequest request, HttpServletResponse response) {
	
		List<StockBean> doAddStock = new ArrayList<StockBean>();
		try {
			
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			JSONObject jsonObject = JSONObject.fromObject(list);
			JSONArray jsonStockBean = jsonObject.getJSONArray("addStockList");
			for (int index = 0; index < jsonStockBean.size(); index++) {
				StockBean stockBean = (StockBean) JSONObject.toBean(jsonStockBean.getJSONObject(index), StockBean.class);
				doAddStock.add(stockBean);
			}
			
			itemServiceFacade.modifyStock(doAddStock);
			modelMap.put("errorCode", 0); 
			modelMap.put("errorMsg", "success");
			
		} catch (IOException e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
}
