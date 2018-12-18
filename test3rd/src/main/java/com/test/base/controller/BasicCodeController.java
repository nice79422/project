package com.test.base.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.base.to.BasicCodeBean;
import com.test.common.sf.CommonServiceFacade;
import com.test.common.to.ListForm;



public class BasicCodeController extends MultiActionController {
	CommonServiceFacade commonServiceFacade;
	private ModelMap modelMap = new ModelMap();
	private ModelAndView modelAndView;
	
	public void setCommonServiceFacade(CommonServiceFacade commonServiceFacade) {
		this.commonServiceFacade = commonServiceFacade;
	}


	public ModelAndView findCodeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		response.setContentType("text/json; charset=UTF-8");
		String code = request.getParameter("code");
		
		try {
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			int dbcount = commonServiceFacade.getRowCount();
			
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<BasicCodeBean> codelist = null;

			if (code != null)
			codelist = commonServiceFacade.getDetailCodeList(sr, er, code);
			else
			codelist = commonServiceFacade.getBasicCodeList(sr, er);

			int pagecount = listForm.getPagecount();
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("list", codelist);
			modelMap.put("errorCode", 0); // ����ó���� ��� 0�� ����
			modelMap.put("errorMsg", "success");
		
		} catch (Exception e) {
		
			e.printStackTrace();
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "�ڵ帮��Ʈ ��ȸ �����Դϴ�.");

		}
			modelAndView = new ModelAndView("jsonView",modelMap);
			return modelAndView;
	}

}
