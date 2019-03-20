package com.test4th.base.controller;

import java.util.List;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.MenuBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.tobesoft.platform.data.PlatformData;

public class MenuController extends AbstractMiplatformMultiActionController {
	/* BaseServiceFacade setting */
	private BaseServiceFacade baseServiceFacade;
	public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
		this.baseServiceFacade = baseServiceFacade;
	}

	/* menu목록을 가져오는 메서드 */
	public void findMenuList(PlatformData inData, PlatformData outData) throws Exception{
		List<MenuBean> menuList=baseServiceFacade.findMenuList();
		datasetBeanMapper.beansToDataset(outData, menuList, MenuBean.class);
	}
}