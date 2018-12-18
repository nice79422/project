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
import com.test.common.to.ListForm;
import com.test.logistics.item.sf.ItemServiceFacade;
import com.test.logistics.item.sf.ItemServiceFacadeImpl;
import com.test.logistics.item.to.ItemBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ItemController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	ModelAndView modelAndView;
	ItemServiceFacade itemServiceFacade = ItemServiceFacadeImpl.getInstance();
	HashMap<String, Object> modelObject = new HashMap<String, Object>();

	public ModelAndView findItemList(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		if (logger.isDebugEnabled()) {
			logger.debug("ItemList - ����");
		}
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
				map.put("errormsg", "ã�� ǰ���� �����ϴ�.");

			int pagecount = listForm.getPagecount();
			
			map.put("page", pagenum);
			map.put("total", pagecount);
			map.put("recoders", "10");
			map.put("list", list);
		
			JSONObject jsonObject = JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);
			
			if (logger.isDebugEnabled()) {
				logger.debug("ItemList - ��");
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("ItemList - ����");
			}
			map.put("errormsg", e.getMessage());

		}
		return null;

	}
	
	public ModelAndView modifyItem(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyItem - ����");
		}
		List<ItemBean> doAddItem = new ArrayList<ItemBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			System.out.println("ITEMLIST:"+list);
			JSONObject jsonObject = JSONObject.fromObject(list);
			System.out.println("jsonObject:"+jsonObject);
			JSONArray jsonItemBean = jsonObject.getJSONArray("addItemList");
			System.out.println("jsonItemBean:"+jsonItemBean);
			for (int index = 0; index < jsonItemBean.size(); index++) {
				ItemBean itemBean = (ItemBean) JSONObject.toBean(jsonItemBean.getJSONObject(index), ItemBean.class);
				doAddItem.add(itemBean);
			}
			
			itemServiceFacade.modifyItem(doAddItem);
		} catch (IOException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyItem - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "ǰ���� �����Դϴ�.");
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("itemBean", new ItemBean());
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("modifyItem - �� ");
		}
		return modelAndView;
	}
	
	
}
