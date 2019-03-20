package com.test.hr.emp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import com.test.common.to.ListForm;
import com.test.hr.emp.sf.EmpServiceFacade;
import com.test.hr.emp.to.EmpBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpController extends MultiActionController {
	
	private EmpServiceFacade empServiceFacade;
	
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();

	public void setEmpServiceFacade(EmpServiceFacade empServiceFacade) {
		this.empServiceFacade = empServiceFacade;
	}


	public ModelAndView findEmpDetail(HttpServletRequest request, HttpServletResponse response) {
		try {
			String empCode = request.getParameter("empCode");
			System.out.println("empCode:"+empCode);
			EmpBean empBean = empServiceFacade.findEmp(empCode);
			System.out.println("empBean:"+empBean);
			modelMap.put("empBean", empBean);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
			
			} catch(Exception e){
				modelMap.put("errorCode", "-1");
				modelMap.put("errormsg", e.getMessage());
			}
			modelAndView = new ModelAndView("jsonView", modelMap);
			return modelAndView;
		}

	public ModelAndView findEmpList(HttpServletRequest request, HttpServletResponse response) {
		try {
			String empCode = request.getParameter("empCode");
			String empName = request.getParameter("empName");
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			int dbcount = empServiceFacade.getRowCount();
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<EmpBean> list = empServiceFacade.getEmpList(sr, er, empCode, empName);
			int pagecount = listForm.getPagecount();
			modelMap.put("page", pagenum);
			modelMap.put("total", pagecount);
			modelMap.put("recoders", "11");
			modelMap.put("list", list);
			
			
		} catch (Exception e) {
			
			modelMap.put("errorCode", "-1");
			modelMap.put("errormsg", e.getMessage());
		}	
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView modifyEmp(HttpServletRequest request, HttpServletResponse response) {

		List<EmpBean> empUpdateList = new ArrayList<EmpBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			JSONObject jsonObject = JSONObject.fromObject(list);
			JSONArray jsonEmpBean = jsonObject.getJSONArray("empUpdateList");
			for (int index = 0; index < jsonEmpBean.size(); index++) {
				EmpBean empBean = (EmpBean) JSONObject.toBean(jsonEmpBean.getJSONObject(index), EmpBean.class);
				empUpdateList.add(empBean);
			}
			
			empServiceFacade.modifyEmp(empUpdateList);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
		} catch (IOException e) {
			
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "����.");
			modelMap.put("empBean", new EmpBean());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView registerEmp(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			request.setCharacterEncoding("UTF-8");
			String EmpBeans = request.getParameter("EmpBean");
			JSONObject jsonObject = JSONObject.fromObject(EmpBeans);
			EmpBean empBean = (EmpBean) JSONObject.toBean(jsonObject, EmpBean.class);
		
			empServiceFacade.registerEmp(empBean);
			modelMap.put("empBean", empBean);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");

			
		} catch (IOException e) {
		
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "������� �����Դϴ�.");
		}
		
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView removeEmp(HttpServletRequest request, HttpServletResponse response) {

		try{
			String deleteId=request.getParameter("deleteCode");
			empServiceFacade.removeEmpList(deleteId);
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
		}catch(Exception e){
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "������ �����Դϴ�.");
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView setEmptyEmp(HttpServletRequest request, HttpServletResponse response) {

		try {
			
			modelMap.put("emptyEmpBean", new EmpBean()); 
			modelMap.put("errorCode", 0);
			modelMap.put("errorMsg", "Success!");
			
		} catch (Exception e) {
			modelMap.clear();
			modelMap.put("errorCode", -1);
			modelMap.put("errorMsg", "������� �����Դϴ�.");
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}


}
