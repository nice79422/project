package com.test.logistics.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.test.common.to.ListForm;
import com.test.logistics.product.sf.ProductServiceFacade;
import com.test.logistics.product.to.MpsBean;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ProductMpsController extends MultiActionController {
	
	ProductServiceFacade productServiceFacade;
	private ModelAndView modelAndView;
	private ModelMap modelMap = new ModelMap();
	

	public void setProductServiceFacade(ProductServiceFacade productServiceFacade) {
		this.productServiceFacade = productServiceFacade;
	}
   	public ModelAndView findContractDetailList(HttpServletRequest request, HttpServletResponse response){
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
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",list);
			modelMap.put("errorCode", 0); // ?��?��?��?��?��?��처占?��?��?��?��?�� ?��?��?��?���? 0?��?��?�� ?��?��?��?��?��?��
			modelMap.put("errorMsg", "success");
			
		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView findMpsList(HttpServletRequest request, HttpServletResponse response){
		try{
			String mpsStatus=request.getParameter("mpsStatus");
			String mrpStatus=request.getParameter("mrpStatus");
			String lineNo=request.getParameter("lineNo");
			String sDate=request.getParameter("startDate");
			String eDate=request.getParameter("endDate");
		
			int pagenum=Integer.parseInt(request.getParameter("page"));
			int rowsize=Integer.parseInt(request.getParameter("rows"));
			int dbcount=productServiceFacade.getMpsRowCount(mpsStatus, mrpStatus, lineNo);
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
			modelMap.put("page",pagenum);
			modelMap.put("total",pagecount);
			modelMap.put("recoders","11");
			modelMap.put("list",lists);
			modelMap.put("errorCode", 0); // ?��?��?��?��?��?��처占?��?��?��?��?�� ?��?��?��?���? 0?��?��?�� ?��?��?��?��?��?��
			modelMap.put("errorMsg", "success");

		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}

	public ModelAndView batchMpsProcess(HttpServletRequest request, HttpServletResponse response){
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
			
			modelMap.put("errorCode", 0); // ?��?��?��?��?��?��처占?��?��?��?��?�� ?��?��?��?���? 0?��?��?�� ?��?��?��?��?��?��
			modelMap.put("errorMsg", "success");
		}catch(Exception e){
			e.printStackTrace();
				modelMap.put("errorCode", -1);
				modelMap.put("errorMsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
		

	public ModelAndView modifyMps(HttpServletRequest request,HttpServletResponse response) {

		ArrayList<MpsBean> bean=new ArrayList<MpsBean>();

		try{
			JSONObject jsonObject=JSONObject.fromObject(request.getParameter("list"));//json?��?��?��?��?��뤄옙 ?��?��?��?��?��?��?��?���? list
			JSONArray jsonMpsBeanList=jsonObject.getJSONArray("mpsBeanList");
			for(int index=0; index<jsonMpsBeanList.size(); index++){
				MpsBean mpsBean=(MpsBean)JSONObject.toBean(jsonMpsBeanList.getJSONObject(index),MpsBean.class);
				bean.add(mpsBean);
			}
			productServiceFacade.mpsModify(bean);
			modelMap.put("errorCode", 0); // ?��?��?��?��?��?��처占?��?��?��?��?�� ?��?��?��?���? 0?��?��?�� ?��?��?��?��?��?��
			modelMap.put("errorMsg", "success");

		}catch(Exception e){
			modelMap.put("errorCode", -1);
			modelMap.put("errormsg", e.getMessage());
		}
		modelAndView = new ModelAndView("jsonView", modelMap);
		return modelAndView;
	}
}
