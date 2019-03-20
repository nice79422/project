package com.test.logistics.item.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.common.to.ListForm;
import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.to.ItemBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ItemController extends MultiActionController {
	
	ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	ItemServiceFacade itemServiceFacade;
	
	public void setItemServiceFacade(ItemServiceFacade itemServiceFacade) {
		this.itemServiceFacade = itemServiceFacade;
	}

	public ModelAndView findItemList(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			String code = request.getParameter("code");
			String itemCode = request.getParameter("itemCode");
			String itemName = request.getParameter("itemName");
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));

			int dbcount = itemServiceFacade.getRowCount();

			ListForm listForm = new ListForm();

			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<ItemBean> list = null;

			if (code == null)
				list = itemServiceFacade.getItemList(sr, er, itemCode, itemName);
			else {
				list = itemServiceFacade.getItemList(code);
			
			}
			if (list == null)
				modelMap.put("errormsg", "ã�� ǰ���� �����ϴ�.");

			int pagecount = listForm.getPagecount();
			
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("recoders", "10");
			modelMap.put("list", list);
			modelMap.put("errorCode", 0); // ����ó���� ��� 0�� ����
			modelMap.put("errorMsg", "success");
		
			
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());

		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;

	}
	
	public ModelAndView modifyItem(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyItem - ����");
		}
		List<ItemBean> doAddItem = new ArrayList<ItemBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			JSONObject jsonObject = JSONObject.fromObject(list);
			JSONArray jsonItemBean = jsonObject.getJSONArray("addItemList");
			for (int index = 0; index < jsonItemBean.size(); index++) {
				ItemBean itemBean = (ItemBean) JSONObject.toBean(jsonItemBean.getJSONObject(index), ItemBean.class);
				doAddItem.add(itemBean);
			}
			
			itemServiceFacade.modifyItem(doAddItem);
			modelMap.put("errorCode", 0); // ����ó���� ��� 0�� ����
			modelMap.put("errorMsg", "success");
			
		} catch (Exception e) {
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
	
}
