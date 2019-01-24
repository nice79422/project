package com.test5th.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.EtcSalBean;
import com.test5th.base.to.OvertimeSalBean;
import com.test5th.base.to.SudangInfoBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class BasicSalaryController{
	/* BaseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

	/* 연장,야간,휴일,기타 수당관련 목록을 불러오는 메서드 */
	@RequestMapping("base/findSalaryList.do")
	public void findSalaryList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<OvertimeSalBean> overTimeSalList=baseServiceFacade.findOvertimeSalList();
		List<EtcSalBean> etcSalList=baseServiceFacade.findEtcSalList();
		datasetBeanMapper.beansToDataset(outData, overTimeSalList, OvertimeSalBean.class);
		datasetBeanMapper.beansToDataset(outData, etcSalList, EtcSalBean.class);
	}

	@RequestMapping("base/batchSudang.do")
	public void batchSudang(HttpServletRequest request, HttpServletResponse response) throws Exception{

		PlatformData inData = (PlatformData) request.getAttribute("inData");
		
		   SudangInfoBean sudangInfoBean=new SudangInfoBean();
			sudangInfoBean.setOverTimeSalList(datasetBeanMapper.datasetToBeans(inData, OvertimeSalBean.class));
			sudangInfoBean.setEtcSalList(datasetBeanMapper.datasetToBeans(inData, EtcSalBean.class));

			baseServiceFacade.batchSudang(sudangInfoBean);


		findSalaryList(request,response);
	}


}