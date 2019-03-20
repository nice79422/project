package com.test4th.base.controller;


import java.util.ArrayList;
import java.util.List;

import com.test4th.base.serviceFacade.BaseServiceFacade;
import com.test4th.base.to.PositionBean;
import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.circumstance.to.HobongBean;
import com.tobesoft.platform.data.PlatformData;


public class PositionController extends AbstractMiplatformMultiActionController{
	/* BaseServiceFacade */
	private BaseServiceFacade baseServiceFacade;
    public void setBaseServiceFacade(BaseServiceFacade baseServiceFacade) {
        this.baseServiceFacade = baseServiceFacade;
    }
    /* 직급목록을 가져오는 메서드 */
	public void findPositionList(PlatformData inData, PlatformData outData) throws Exception {
        List<PositionBean> positionList=baseServiceFacade.findPositionList();
        datasetBeanMapper.beansToDataset(outData, positionList, PositionBean.class);

      }
	
	/*직급하나 조회해서 호봉 가져오는 메서드*/
	public void findHobongList(PlatformData inData, PlatformData outData) throws Exception {
	
		String positionCode = inData.getVariable("positionCode").getValue().asString();
		PositionBean position=baseServiceFacade.findPosition(positionCode);
		 List<HobongBean> hobongList=position.getHobongList();
		 datasetBeanMapper.beansToDataset(outData, hobongList, HobongBean.class);
	}
}