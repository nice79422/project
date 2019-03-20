package com.test5th.hr.circumstance.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.serviceFacade.CircumstanceServiceFacade;
import com.test5th.hr.circumstance.to.BaseWorkTimeBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class BaseWorkTimeController{
	/* CircumstanceServiceFacade setting */
	@Autowired
	private CircumstanceServiceFacade circumstanceServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;

	/* 모든 기본근무시간목록을 가져오는 메서드 */
	@RequestMapping("hr/circumstance/findBaseWorkTimeList.do")
	public void findBaseWorkTimeList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<BaseWorkTimeBean> baseWorkTimeList = circumstanceServiceFacade.findBaseWorkTimeList();
       datasetBeanMapper.beansToDataset(outData, baseWorkTimeList, BaseWorkTimeBean.class);
    }
/*	
	// 해당년도의 근무시간 삭제 
	public void removeBaseWorkTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		System.out.println(inData);
		circumstanceServiceFacade.removeBaseWorkTime(baseWorkTimeBean);
		System.out.println(baseWorkTimeBean);
		findBaseWorkTimeList(inData,outData);
	}
*/
	

	// 해당년도에 기본근무시간을 추가하는 메서드 
	@RequestMapping("hr/circumstance/addBaseWorkTime.do")
	public void addBaseWorkTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		circumstanceServiceFacade.addBaseWorkTime(baseWorkTimeBean);
		findBaseWorkTimeList(request,response);
	}

	// 해당년도의 기본근무시간을 수정하는 메서드 
	@RequestMapping("hr/circumstance/editBaseWorkTime.do")
	public void editBaseWorkTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		circumstanceServiceFacade.editBaseWorkTime(baseWorkTimeBean);
		findBaseWorkTimeList(request,response);
	}

}