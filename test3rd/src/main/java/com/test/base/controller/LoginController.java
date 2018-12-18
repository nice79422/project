package com.test.base.controller;

import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.base.exception.IdNotFoundException;
import com.test.base.exception.PwMissMatchException;
import com.test.base.sf.BaseServiceFacade;
import com.test.hr.emp.to.EmpBean;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginController extends MultiActionController {
	
	BaseServiceFacade baseServiceFacade ;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	private MessageSource messageSource;
	
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}

	
	
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession();
			String empCode = request.getParameter("empCode");
			String pw = request.getParameter("pw"); // ���̵� ��� �޾��ְ�
			String viewName = null; 
			
			try {
				EmpBean bean = baseServiceFacade.login(empCode, pw);	
			if (bean != null) {
			
				request.getSession().setAttribute("empCode", bean.getEmpCode());
				request.getSession().setAttribute("name", bean.getEmpName());
				request.getSession().setAttribute("imageName", bean.getImage());
				request.getSession().setAttribute("email", bean.getEmail());
				
				viewName = "redirect:" + "/emp/login.html";
			}
		
		
			} catch (IdNotFoundException e) {
				
				String errorMsg = messageSource.getMessage("IdNotFoundException", new Object[] { empCode },
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -1);
				modelMap.put("errorMsg", errorMsg);
				request.getSession().setAttribute("flag", true);
				request.getSession().setAttribute("errorMsg", errorMsg);
				viewName = "redirect:" +"/loginform.html";

			} catch (PwMissMatchException e) {
				System.out.println("pw�ͼ���");
				String errorMsg = messageSource.getMessage("PwMissMatchException", null,
						(Locale) session.getAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME));
				modelMap.put("errorCode", -1);
				modelMap.put("errorMsg", errorMsg);
				request.getSession().setAttribute("errorMsg", errorMsg);
				request.getSession().setAttribute("flag", false);
				viewName = "redirect:" + "/loginform.html";
			}
			modelAndView = new ModelAndView(viewName, modelMap);
			return modelAndView;
		}
	
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		
		request.getSession().invalidate();
		modelAndView = new ModelAndView("redirect:" + "/loginform.html", null);
	     return modelAndView;
	}
}
