package com.test.common.servlet.mvc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.servlet.ModelAndView;

public abstract class MultiActionController extends AbstractController{
	
	 public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	      String methodName = request.getParameter("method");//jsp ���� �Ķ���ͷ� ���� �޼��带 ���⼭ �޴´�
	      Class<?>[] parameters = new Class<?>[]{HttpServletRequest.class,HttpServletResponse.class}; //���׸� �迭 ->�Ķ���� Ÿ���� �����ֱ����� ���
	      Class<?> cl = this.getClass(); //��Ÿ�ӽ� ���ʷ� ���۵� ��Ʈ�ѷ��� �޾ƿ´�

	      try{
	         if (logger.isDebugEnabled()) {
	            logger.debug(methodName + " ���� ");
	         }
                                                   //�ش�Ŭ������ �ش�޼���� �������ش�.
	         ModelAndView modelAndView=(ModelAndView)cl.getMethod(methodName, parameters).invoke(cl.newInstance(),request,response);
	         //                           getMethod(�޼����,���׸� �迭)�ش�޼����� ������ �������ִ�Ŭ����->�޼���Ŭ���� ����,  Ŭ����.invoke->�ش�Ŭ������ �޼��带 �����Ų��
	         if (logger.isDebugEnabled()) {
	            logger.debug(methodName + " ���� ");
	         }
	         return modelAndView;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return null;
	   }
}