package kr.co.seoulit.angular.controller;




import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import kr.co.seoulit.angular.to.MemberTO;
import net.sf.json.JSONObject;
import kr.co.seoulit.angular.serviceFacade.AngularServiceFacade;

@Controller
public class AngularTestController {

	private ModelAndView modelAndView = null;
	private ModelMap modelMap = new ModelMap();
	@Autowired
	private AngularServiceFacade angularServiceFacade;


	@RequestMapping(value = "angular/findMemberList.do", method = RequestMethod.GET)
	public ModelAndView findMemberList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<MemberTO> members = angularServiceFacade.findMemberList();
		modelMap.put("members", members); 
		modelAndView = new ModelAndView("jsonView",modelMap);
		return modelAndView;
	}
	
	@RequestMapping("angular/insertMember.do")
	public void joinMember(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberTO member = new MemberTO();
	      String id = request.getParameter("id");
	      String pw = request.getParameter("pw");
	      String addr = request.getParameter("addr");
	      String tel = request.getParameter("tel");
	      member.setId(id);
	      member.setPw(pw);
	      member.setAddr(addr);
	      member.setTel(tel);
	      angularServiceFacade.joinMember(member);
	}
	
	@RequestMapping("angular/deleteMember.do")
		public void deleteMemeber(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberTO member = new MemberTO();
	      String id = request.getParameter("id");
	      member.setId(id);
	      angularServiceFacade.deleteMember(member);
	}

	@RequestMapping("angular/login.do")
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		MemberTO member=  angularServiceFacade.login(id,pw);
		return modelAndView;

	}
}