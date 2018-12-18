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
        List<HobongBean> hobongList=new ArrayList<HobongBean>();
        for(PositionBean positionBean : positionList){
        	List<HobongBean> hobongBeanList=positionBean.getHobongList();
        	/* 자바로 ArrayList복사하는 메서드 : addAll */
        	hobongList.addAll(hobongBeanList);
        	}
        datasetBeanMapper.beansToDataset(outData, positionList, PositionBean.class);
    	datasetBeanMapper.beansToDataset(outData, hobongList, HobongBean.class);
   
      }


}