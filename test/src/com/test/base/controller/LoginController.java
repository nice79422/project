package com.test.base.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.base.sf.BaseServiceFacade;
import com.test.base.sf.BaseServiceFacadeImpl;
import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.hr.emp.to.EmpBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class LoginController extends MultiActionController {
	
	protected final Log logger = LogFactory.getLog(getClass());
	BaseServiceFacade baseServiceFacade = BaseServiceFacadeImpl.getInstance();
	ModelAndView modelAndView;



	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		try {
			if (logger.isDebugEnabled()) {
				logger.debug("Login - 시작");
			}
			String empCode = request.getParameter("empCode");
			String pw = request.getParameter("pw"); // 아이디 비번 받아주고
			EmpBean bean = baseServiceFacade.login(empCode, pw); // 여기서는 true
																// false반환하지만
																// 세션에는 이름이
																// 올라가야하기에 true
																// 안됨
			if (bean != null) {
				request.getSession().setAttribute("empCode", bean.getEmpCode());
				request.getSession().setAttribute("name", bean.getEmpName());
				
				modelAndView = new ModelAndView("redirect:" + request.getContextPath() +"/emp/login.html", null);
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("Login - 에러");
			}
		
			request.setAttribute("errormsg", e.getMessage());
			modelAndView = new ModelAndView("redirect:" + request.getContextPath() + "/loginform.html", null);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("Login - 끝");
			}
		}
		return modelAndView;
	}
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("EmpLogout - 시작");
		}
		request.getSession().invalidate();
		String viewName = "loginform";
		ModelAndView modelAndView = new ModelAndView(viewName, null);
		if (logger.isDebugEnabled()) {
			logger.debug("EmpLogout - 끝 ");
		}
		return modelAndView;
	}
}
