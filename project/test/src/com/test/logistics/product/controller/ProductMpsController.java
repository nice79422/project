package com.test.logistics.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.test.common.servlet.ModelAndView;
import com.test.common.servlet.mvc.MultiActionController;
import com.test.common.to.ListForm;
import com.test.logistics.product.sf.ProductServiceFacade;
import com.test.logistics.product.sf.ProductServiceFacadeImpl;
import com.test.logistics.product.to.MpsBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductMpsController extends MultiActionController {
	protected final Log logger = LogFactory.getLog(getClass());
	ProductServiceFacade productServiceFacade=ProductServiceFacadeImpl.getInstance();
	HashMap<String,Object> map=new HashMap<String,Object>();
	ModelAndView modelAndView;
	List<MpsBean> lists;
	

	public void findContractDetailList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("findContractDetailList-시작");}
		try{
			String mpsUse=request.getParameter("mpsStatus");
			String mpsUseStatus=null;
			if(mpsUse!=null)
				mpsUseStatus=mpsUse;

			int pagenum=1;
			if(request.getParameter("page")!=null)
				pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=100;
			if(request.getParameter("rows")!=null)
				rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=productServiceFacade.getMpsRowCount(mpsUseStatus);
		
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			List<MpsBean> list=productServiceFacade.findMpsList(sr, er, mpsUseStatus);
			int pagecount=listForm.getPagecount();
			map.put("page",pagenum);
			map.put("total",pagecount);
			map.put("recoders","11");
			map.put("list",list);

			JSONObject jsonObject=JSONObject.fromObject(map);
			response.getWriter().println(jsonObject);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("findContractDetailList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/product/MpsForm",map);
		}
		if (logger.isDebugEnabled()) { logger.debug("findContractDetailList-끝");}
	}

	public void findMpsList(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("getMpsList-시작");}
		try{
			String mpsStatus=request.getParameter("mpsStatus");
			String mrpStatus=request.getParameter("mrpStatus");
			String lineNo=request.getParameter("lineNo");
			String sDate=request.getParameter("startDate");
			String eDate=request.getParameter("endDate");
		
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=productServiceFacade.getMpsRowCount(mpsStatus/*, mrpStatus, lineNo*/);
			ListForm listForm=new ListForm();
			listForm.setRowsize(rowsize);
			listForm.setPagenum(pagenum);
			listForm.setDbcount(dbcount);
			
			int sr=listForm.getStartrow();
			int er=listForm.getEndrow();
			
			List<MpsBean> lists=null;
			if(lineNo!=null)  
			lists=productServiceFacade.findMpsList(sr, er, mpsStatus, mrpStatus , lineNo, sDate, eDate);
			else  
			lists=productServiceFacade.findMpsList(sr, er);
			
			int pagecount=listForm.getPagecount();
			map.put("page",pagenum);
			map.put("total",pagecount);
			/*map.put("recoders","11");*/
			map.put("list",lists);
			JSONObject json=JSONObject.fromObject(map);
			response.getWriter().println(json);
			modelAndView=null;
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("getMpsList-에러");}
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("logistics/product/mpsform",map);
		}
		if (logger.isDebugEnabled()) { logger.debug("getMpsList-끝");}
	}

	public ModelAndView batchMpsProcess(HttpServletRequest request, HttpServletResponse response){
		if (logger.isDebugEnabled()) { logger.debug("batchMpsProcess-시작");}
		HashMap<String,Object> modelObject = new HashMap<String,Object>();
		List<MpsBean> mpsList=new ArrayList<MpsBean>();
		try{
			request.setCharacterEncoding("UTF-8");

			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("mpsList"));

			JSONArray mpsBeanList=jsonObject.getJSONArray("mpsBeanList");

			for(int index=0; index<mpsBeanList.size(); index++){
				MpsBean mpsBean=(MpsBean)JSONObject.toBean(mpsBeanList.getJSONObject(index), MpsBean.class);
				mpsList.add(mpsBean);
			}
		

			productServiceFacade.batchMpsProcess(mpsList);
		}catch(Exception e){
			if (logger.isFatalEnabled()) { logger.fatal("batchMpsProcess-에러");}
				modelObject.clear();
				modelObject.put("errorCode", -1);
				modelObject.put("errorMsg", "Failed");
				JSONObject json = JSONObject.fromObject(modelObject);
				try {
					response.getWriter().println(json);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				modelAndView=null;
		}
		modelAndView=null;
		if (logger.isDebugEnabled()) { logger.debug("batchMpsProcess-끝");}
		return modelAndView;
	}

	public ModelAndView modifyMps(HttpServletRequest request,
			HttpServletResponse response) {
		if (logger.isDebugEnabled()) { logger.debug("MrpModifyController - start");}
		ArrayList<MpsBean> bean=new ArrayList<MpsBean>();

		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));//json형태로 뽑아줬던 list
			JSONArray jsonMpsBeanList=jsonObject.getJSONArray("mpsBeanList");
			for(int index=0; index<jsonMpsBeanList.size(); index++){
				MpsBean mpsBean=(MpsBean)JSONObject.toBean(jsonMpsBeanList.getJSONObject(index),MpsBean.class);
				bean.add(mpsBean);
			}
			productServiceFacade.mpsModify(bean);
			if (logger.isDebugEnabled()) { logger.debug("MrpModifyController - end"); }
			return null;
		}catch(Exception e){
			map.put("errormsg", e.getMessage());
			modelAndView=new ModelAndView("/error",map);
			return modelAndView;
		}
	}
}
