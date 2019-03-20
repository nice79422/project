package com.test.common.servlet.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;

public abstract class AbstractController implements Controller {
   protected final Log logger = LogFactory.getLog(this.getClass());
   		//���ø��޼��屸�� ����
   public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
      String controllerFullName = this.getClass().getName();
      String controllerShortName = controllerFullName.substring(controllerFullName.lastIndexOf(".")+1);
      if (logger.isDebugEnabled()) { 
         logger.debug(controllerShortName + " ���� ");
      }

      // �� �κп� ��Ʈ�ѷ����� ���������� �����ؾ� �� ������ ������ �� ����
      
      response.setHeader("Pragma", "no-cache");
      response.setHeader("Cache-Control", "no-cache");
      response.addHeader("Cache-Control", "no-store");
      // ĳ�� �������� �ʰ� �ϴ� �ҽ�
    
      ModelAndView modelAndView = handleRequestInternal(request, response);

      if (logger.isDebugEnabled()) {
         logger.debug(controllerShortName + " ���� ");
      }
      return modelAndView;
   }

   public abstract ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response);

}