package com.test4th.hr.circumstance.controller;

import java.util.List;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.circumstance.service.CircumstanceServiceFacade;
import com.test4th.hr.circumstance.to.BaseWorkTimeBean;
import com.tobesoft.platform.data.PlatformData;

public class BaseWorkTimeController extends AbstractMiplatformMultiActionController{
	/* CircumstanceServiceFacade setting */
	private CircumstanceServiceFacade circumstanceServiceFacade;
	public void setCircumstanceServiceFacade(CircumstanceServiceFacade circumstanceServiceFacade) {
		this.circumstanceServiceFacade = circumstanceServiceFacade;
	}

	/* 모든 기본근무시간목록을 가져오는 메서드 */
	public void findBaseWorkTimeList(PlatformData inData, PlatformData outData) throws Exception {
	   List<BaseWorkTimeBean> baseWorkTimeList = circumstanceServiceFacade.findBaseWorkTimeList();
       datasetBeanMapper.beansToDataset(outData, baseWorkTimeList, BaseWorkTimeBean.class);
    }
/*	
	// 해당년도의 근무시간 삭제 
	public void removeBaseWorkTime(PlatformData inData, PlatformData outData) throws Exception {
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		System.out.println(inData);
		circumstanceServiceFacade.removeBaseWorkTime(baseWorkTimeBean);
		System.out.println(baseWorkTimeBean);
		findBaseWorkTimeList(inData,outData);
	}
*/
	

	// 해당년도에 기본근무시간을 추가하는 메서드 
	public void addBaseWorkTime(PlatformData inData, PlatformData outData) throws Exception {
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		circumstanceServiceFacade.addBaseWorkTime(baseWorkTimeBean);
		findBaseWorkTimeList(inData,outData);
	}

	// 해당년도의 기본근무시간을 수정하는 메서드 
	public void editBaseWorkTime(PlatformData inData, PlatformData outData) throws Exception {
		BaseWorkTimeBean baseWorkTimeBean = datasetBeanMapper.datasetToBean(inData, BaseWorkTimeBean.class);
		circumstanceServiceFacade.editBaseWorkTime(baseWorkTimeBean);
		findBaseWorkTimeList(inData,outData);
	}

}