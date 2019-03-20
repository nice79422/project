package com.test5th.base.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.PositionBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.test5th.hr.circumstance.to.HobongBean;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class PositionController{
	/* BaseServiceFacade */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	
	
    /* 직급목록을 가져오는 메서드 */
	@RequestMapping("base/findPositionList.do")
	public void findPositionList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
        List<PositionBean> positionList=baseServiceFacade.findPositionList();
        List<PositionBean> positionBean=new ArrayList<>();
        for(PositionBean positionTO:positionList) {
        	PositionBean pos=new PositionBean();
        	pos.setPositionCode(positionTO.getPositionCode());
        	pos.setPositionName(positionTO.getPositionName());
        	positionBean.add(pos);
        }
        
        datasetBeanMapper.beansToDataset(outData, positionBean, PositionBean.class);

      }
	
	/*직급하나 조회해서 호봉 가져오는 메서드*/
	@RequestMapping("base/findHobongList.do")
	public void findHobongList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		PlatformData inData = (PlatformData) request.getAttribute("inData");
		String positionCode = inData.getVariable("positionCode").getString();
		PositionBean position=baseServiceFacade.findPosition(positionCode);
		 List<HobongBean> hobongList=position.getHobongList();
		 datasetBeanMapper.beansToDataset(outData, hobongList, HobongBean.class);
	}
}