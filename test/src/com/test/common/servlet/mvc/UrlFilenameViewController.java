package com.test.common.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.servlet.ModelAndView;

public class UrlFilenameViewController implements Controller{

	@Override // �߱� NullAction �� ���� �ƹ��� �������ϰ� ȭ���̵����Ѵ�.
	public ModelAndView handleRequest/*�߻�޼���*/ (HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		String uri=request.getRequestURI();  // /mvc/menu.html
		String contextPath=request.getContextPath(); // /mvc
		int sIndex=contextPath.length()+1; // 5
		int eIndex=uri.lastIndexOf(".");  //9
		String viewName=uri.substring(sIndex,eIndex); // menu
		ModelAndView modelAndView=new ModelAndView(viewName,null);
		return modelAndView;
	}
}
