package com.test5th.base.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test5th.base.serviceFacade.BaseServiceFacade;
import com.test5th.base.to.MenuBean;
import com.test5th.common.mapper.DatasetBeanMapper;
import com.tobesoft.xplatform.data.PlatformData;

@Controller
public class MenuController {
	/* BaseServiceFacade setting */
	@Autowired
	private BaseServiceFacade baseServiceFacade;
	@Autowired
	private DatasetBeanMapper datasetBeanMapper;
	

	/* menu목록을 가져오는 메서드 */
	@RequestMapping("base/findMenuList.do")
	public void findMenuList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		PlatformData outData = (PlatformData) request.getAttribute("outData");
		List<MenuBean> menuList=baseServiceFacade.findMenuList();
		datasetBeanMapper.beansToDataset(outData, menuList, MenuBean.class);
	}
}