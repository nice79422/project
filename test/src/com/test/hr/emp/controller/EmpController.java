package com.test.hr.emp.controller;

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
import com.test.hr.emp.sf.EmpServiceFacade;
import com.test.hr.emp.sf.EmpServiceFacadeImpl;
import com.test.hr.emp.to.EmpBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class EmpController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	EmpServiceFacade empServiceFacade = EmpServiceFacadeImpl.getInstance();
	HashMap<String, Object> modelObject = new HashMap<String, Object>();
	ModelAndView modelAndView;




	

	public ModelAndView findEmpDetail(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmpDetail - ����");
		}
		String empCode = request.getParameter("empCode");
		EmpBean empBean = empServiceFacade.findEmp(empCode);
		modelObject.put("empBean", empBean);
		JSONObject jSONObject = JSONObject.fromObject(modelObject);
		try {
			response.getWriter().println(jSONObject);
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findEmpDetail - ����");
			}
			e.printStackTrace();
		}
		if (logger.isDebugEnabled()) {
			logger.debug("findEmpDetail - �� ");
		}
		return modelAndView;
	}

	public ModelAndView findEmpList(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("findEmpList - ����");
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
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
			map.put("page", pagenum);
			map.put("total", pagecount);
			map.put("recoders", "11");
			map.put("list", list);
			JSONObject jsonObject = JSONObject.fromObject(map);
			
			response.getWriter().println(jsonObject);
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findEmpList - ����");
			}
			map.put("errorCode", "-1");
			map.put("errormsg", e.getMessage());
			JSONObject jsonObject = JSONObject.fromObject(map);
			try {
				response.getWriter().println(jsonObject);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			if (logger.isDebugEnabled()) {

				logger.debug("findEmpList - �� ");
			}
		}
		return null;
	}

	public ModelAndView modifyEmp(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyEmp - ����");
		}
		List<EmpBean> empUpdateList = new ArrayList<EmpBean>();
		try {
			request.setCharacterEncoding("UTF-8");
			String list = request.getParameter("list");
			System.out.println("EMPLIST:"+list);
			JSONObject jsonObject = JSONObject.fromObject(list);
			System.out.println("jsonObject:"+jsonObject);
			JSONArray jsonEmpBean = jsonObject.getJSONArray("empUpdateList");
			System.out.println("jsonEmpBean:"+jsonEmpBean);
			for (int index = 0; index < jsonEmpBean.size(); index++) {
				EmpBean empBean = (EmpBean) JSONObject.toBean(jsonEmpBean.getJSONObject(index), EmpBean.class);
				empUpdateList.add(empBean);
			}
			
			empServiceFacade.modifyEmp(empUpdateList);
		} catch (IOException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("modifyEmp - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "������� �����Դϴ�.");
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("empBean", new EmpBean());
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("modifyEmp - �� ");
		}
		return modelAndView;
	}

	public ModelAndView registerEmp(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("EmpRegister - ����");
		}
		try {
			request.setCharacterEncoding("UTF-8");
			String EmpBeans = request.getParameter("EmpBean");
			
			JSONObject jsonObject = JSONObject.fromObject(EmpBeans);
			EmpBean empBean = (EmpBean) JSONObject.toBean(jsonObject, EmpBean.class);
		
			empServiceFacade.registerEmp(empBean);
			modelObject.put("empBean", empBean);

			if (logger.isDebugEnabled()) {
				logger.debug("EmpRegister - �� ");
			}

			return null;
		} catch (IOException e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("EmpRegister - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "������ �����Դϴ�.");
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("emptyEmpBean", new EmpBean());
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				if (logger.isFatalEnabled()) {
					logger.fatal("EmpRegister - ����");
				}
				e1.printStackTrace();
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("EmpRegister - ��");
		}
		modelAndView = null;
		return modelAndView;
	}

	public ModelAndView removeEmp(HttpServletRequest request, HttpServletResponse response) {
		if(logger.isDebugEnabled()){
			   logger.debug("EmpRemove - ����");
		   }
		try{
			String deleteId=request.getParameter("deleteCode");
			EmpServiceFacadeImpl.getInstance().removeEmpList(deleteId);
		}catch(Exception e){
			if(logger.isFatalEnabled()){
	    		 logger.fatal("EmpRemove - ����");
	    	 }
		}
		if(logger.isDebugEnabled()){
			   logger.debug("EmpRemove - ��");
		   }
		return null;
	}

	public ModelAndView setEmptyEmp(HttpServletRequest request, HttpServletResponse response) {

		try {
			if (logger.isDebugEnabled()) {
				logger.debug("setEmptyEmp - ����");
			}
			modelObject.put("emptyEmpBean", new EmpBean()); // ��ü�� �����ش�
			JSONObject json = JSONObject.fromObject(modelObject);
			modelObject.put("errorCode", 0);
			modelObject.put("errorMsg", "Success!");
			response.getWriter().println(json);
			modelAndView = null;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("setEmptyEmp - ����");
			}
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "�������������Դϴ�");
			JSONObject json = JSONObject.fromObject(modelObject);
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			modelAndView = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("setEmptyEmp - ��");
		}
		return modelAndView;
	}

}
