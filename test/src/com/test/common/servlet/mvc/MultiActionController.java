package com.test.common.servlet.mvc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.common.servlet.ModelAndView;

public abstract class MultiActionController extends AbstractController{
	
	 public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
	      String methodName = request.getParameter("method");//jsp 에서 파라메터로 던진 메서드를 여기서 받는다
	      Class<?>[] parameters = new Class<?>[]{HttpServletRequest.class,HttpServletResponse.class}; //제네릭 배열 ->파라미터 타입을 던져주기위해 사용
	      Class<?> cl = this.getClass(); //런타임시 최초로 시작된 컨트롤러를 받아온다

	      try{
	         if (logger.isDebugEnabled()) {
	            logger.debug(methodName + " 시작 ");
	         }
                                                   //해당클래스의 해당메서드로 매핑해준다.
	         ModelAndView modelAndView=(ModelAndView)cl.getMethod(methodName, parameters).invoke(cl.newInstance(),request,response);
	         //                           getMethod(메서드명,제네릭 배열)해당메서드의 정보를 가지고있는클래스->메서드클래스 리턴,  클래스.invoke->해당클래스의 메서드를 실행시킨다
	         if (logger.isDebugEnabled()) {
	            logger.debug(methodName + " 종료 ");
	         }
	         return modelAndView;
	      } catch (Exception e) {
	         e.printStackTrace();
	      }

	      return null;
	   }
}