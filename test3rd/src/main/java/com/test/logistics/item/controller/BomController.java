package com.test.logistics.item.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.to.BomBean;


public class BomController extends MultiActionController {
	ItemServiceFacade itemServiceFacade;
	ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	
	public void setItemServiceFacade(ItemServiceFacade itemServiceFacade) {
		this.itemServiceFacade = itemServiceFacade;
	}

	public ModelAndView findBomList(HttpServletRequest request, HttpServletResponse response){
		
		String searchCode=request.getParameter("searchStr");
		String bom = request.getParameter("bom");
		try{
			List<BomBean> list=itemServiceFacade.findBom(searchCode, bom);
			modelMap.put("list",list);
			modelMap.put("errorCode", 0); // ����ó���� ��� 0�� ����
			modelMap.put("errorMsg", "success");
						
		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}
	

	/*public ModelAndView modifyBom(HttpServletRequest request, HttpServletResponse response) {
		
		List<BomBean> doAddBom = new ArrayList<BomBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			JSONObject jsonObject = JSONObject.fromObject(list);
			JSONArray jsonBomBean = jsonObject.getJSONArray("addBomList");
			for (int index = 0; index < jsonBomBean.size(); index++) {
				BomBean bomBean = (BomBean) JSONObject.toBean(jsonBomBean.getJSONObject(index), BomBean.class);
				doAddBom.add(bomBean);
			}
			itemServiceFacade.modifyBom(doAddBom);
			modelMap.put("errorCode", 0); // ����ó���� ��� 0�� ����
			modelMap.put("errorMsg", "success");
		} catch (Exception e) {
			
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}*/
}
