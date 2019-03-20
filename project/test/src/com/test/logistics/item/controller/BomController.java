package com.test.logistics.item.controller;


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
import com.test.common.transaction.DataSourceTransactionManager;
import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.sf.ItemServiceFacadeImpl;
import com.test.logistics.item.to.BomBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class BomController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	ItemServiceFacade itemServiceFacade=ItemServiceFacadeImpl.getInstance();
	DataSourceTransactionManager dataSourceTransactionManager=
			DataSourceTransactionManager.getInstance();
ModelAndView modelAndView;
	public BomController(){}
	HashMap<String, Object> modelObject = new HashMap<String, Object>();




	public ModelAndView findBomList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("getBom-시작");}
		String searchCode=request.getParameter("searchStr");
		HashMap<String,Object> map=new HashMap<String,Object>();
		try{
			
			List<BomBean> list=itemServiceFacade.findBom(searchCode);
			map.put("list",list);
			JSONObject jsonObject=JSONObject.fromObject(map);
			
			response.getWriter().println(jsonObject);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("getBom-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/item/findBOM",map);
			return modelAndView;
		}
		if (logger.isDebugEnabled()) { logger.debug("getBom-끝");}
		return modelAndView;
	}
	

	public ModelAndView modifyBom(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBom - 시작");
		}
		List<BomBean> doAddBom = new ArrayList<BomBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			System.out.println("BOMLIST:"+list);
			JSONObject jsonObject = JSONObject.fromObject(list);
			System.out.println("jsonObject:"+jsonObject);
			JSONArray jsonBomBean = jsonObject.getJSONArray("addBomList");
			System.out.println("jsonBomBean:"+jsonBomBean);
			for (int index = 0; index < jsonBomBean.size(); index++) {
				BomBean bomBean = (BomBean) JSONObject.toBean(jsonBomBean.getJSONObject(index), BomBean.class);
				doAddBom.add(bomBean);
			}
			
			itemServiceFacade.modifyBom(doAddBom);
		} catch (IOException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyBom - 에러");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "BOM등록 오류입니다.");
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("bomBean", new BomBean());
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBom - 끝 ");
		}
		return modelAndView;
	}
	
	
	
}
