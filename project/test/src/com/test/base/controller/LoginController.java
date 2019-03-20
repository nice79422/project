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
				logger.debug("Login - ����");
			}
			String empCode = request.getParameter("empCode");
			String pw = request.getParameter("pw"); // ���̵� ��� �޾��ְ�
			EmpBean bean = baseServiceFacade.login(empCode, pw); // ���⼭�� true
																// false��ȯ������
																// ���ǿ��� �̸���
																// �ö󰡾��ϱ⿡ true
																// �ȵ�
			if (bean != null) {
				request.getSession().setAttribute("empCode", bean.getEmpCode());
				request.getSession().setAttribute("name", bean.getEmpName());
				
				modelAndView = new ModelAndView("redirect:" + request.getContextPath() +"/emp/login.html", null);
			}
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("Login - ����");
			}
		
			request.setAttribute("errormsg", e.getMessage());
			modelAndView = new ModelAndView("redirect:" + request.getContextPath() + "/loginform.html", null);
		} finally {
			if (logger.isDebugEnabled()) {
				logger.debug("Login - ��");
			}
		}
		return modelAndView;
	}
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		if (logger.isDebugEnabled()) {
			logger.debug("EmpLogout - ����");
		}
		request.getSession().invalidate();
		String viewName = "loginform";
		ModelAndView modelAndView = new ModelAndView(viewName, null);
		if (logger.isDebugEnabled()) {
			logger.debug("EmpLogout - �� ");
		}
		return modelAndView;
	}
}
