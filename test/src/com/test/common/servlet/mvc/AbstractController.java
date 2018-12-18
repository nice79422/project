package com.test.common.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;

public abstract class AbstractController implements Controller {
   protected final Log logger = LogFactory.getLog(this.getClass());
   		//템플릿메서드구현 패턴
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
      String controllerFullName = this.getClass().getName();
      String controllerShortName = controllerFullName.substring(controllerFullName.lastIndexOf(".")+1);
      if (logger.isDebugEnabled()) { 
         logger.debug(controllerShortName + " 시작 ");
      }

      // 이 부분에 컨트롤러들이 공통적으로 수행해야 할 로직을 세팅할 수 있음
      
      response.setHeader("Pragma", "no-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.addHeader("Cache-Control", "no-store");
      // 캐시 저장하지 않게 하는 소스
    
      ModelAndView modelAndView = handleRequestInternal(request, response);

      if (logger.isDebugEnabled()) {
         logger.debug(controllerShortName + " 종료 ");
      }
      return modelAndView;
   }

   public abstract ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response);

}