package com.test.base.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.sf.CommonServiceFacade;
import com.test.common.sf.CommonServiceFacadeImpl;
import com.test.common.to.BasicCodeBean;
import com.test.common.to.ListForm;

import net.sf.json.JSONObject;

public class BasicCodeController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());

	CommonServiceFacade commonServiceFacade = CommonServiceFacadeImpl.getInstance();
	HashMap<String, Object> modelObject = new HashMap<String, Object>();
	private ModelAndView modelAndView;



	public ModelAndView findCodeList(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		if (logger.isDebugEnabled()) {
			logger.debug("findCodeLsit - 시작");
		}
		response.setContentType("text/json; charset=UTF-8");
		String code = request.getParameter("code");
		
		try {
			int pagenum = Integer.parseInt(request.getParameter("page"));
			int rowsize = Integer.parseInt(request.getParameter("rows"));
			int dbcount = commonServiceFacade.getRowCount();
			
			ListForm listForm = new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr = listForm.getStartrow();
			int er = listForm.getEndrow();
			List<BasicCodeBean> codelist = null;

			if (code != null)
			codelist = commonServiceFacade.getDetailCodeList(sr, er, code);
			else
			codelist = commonServiceFacade.getBasicCodeList(sr, er);

			int pagecount = listForm.getPagecount();
			modelObject.put("page", pagenum);
			modelObject.put("total", pagecount);
			modelObject.put("list", codelist);
			modelObject.put("errorCode", 0); // 에러처리시 사용 0은 성공
			modelObject.put("errorMsg", "success");
			JSONObject json = JSONObject.fromObject(modelObject);
			response.getWriter().println(json);
			System.out.println("findCodeList - End!");
			modelAndView = null;
		} catch (Exception e) {
			if (logger.isFatalEnabled()) {
				logger.fatal("findCodeList - 에러");
			}

			e.printStackTrace();
			modelObject.clear();
			modelObject.put("errorCode", -1);
			modelObject.put("errorMsg", "코드리스트 조회 오류입니다.");
			JSONObject json = JSONObject.fromObject(modelObject);
			
			try {
				response.getWriter().println(json);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			modelAndView = null;
		}
		if (logger.isDebugEnabled()) {
			logger.debug("findCodeList - 끝 ");
		}
		return modelAndView;
	}

}
