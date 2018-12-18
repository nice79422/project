package com.test4th.hr.circumstance.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.controller.AbstractMiplatformMultiActionController;
import com.test4th.hr.circumstance.service.CircumstanceServiceFacade;
import com.test4th.hr.circumstance.to.HolidayBean;
import com.tobesoft.platform.data.PlatformData;

public class HolidayController extends AbstractMiplatformMultiActionController{
	/* CircumstanceServiceFacade setting */
	private CircumstanceServiceFacade circumstanceServiceFacade;
	public void setCircumstanceServiceFacade(CircumstanceServiceFacade circumstanceServiceFacade) {
		this.circumstanceServiceFacade = circumstanceServiceFacade;
	}
	
	/* 선택된 기간의 휴일목록을 가져오는 메서드 */
	public void findHolidayList(PlatformData inData, PlatformData outData) throws Exception {
		String fromDate = inData.getVariable("fromDate").getValue().getString();
		String toDate = inData.getVariable("toDate").getValue().getString();
		
		//System.out.println("fromDate----"+fromDate+"---toDate-----"+toDate);
		
		Map<String, Object> dateList=new HashMap<>();
		dateList.put("fromDate", fromDate);
		dateList.put("toDate", toDate);
		
		List<HolidayBean> holidayList=circumstanceServiceFacade.findHolidayList(dateList);
		datasetBeanMapper.beansToDataset(outData, holidayList, HolidayBean.class);
    }
	
	// 휴일관련 일괄처리를 하는 메서드 
	public void batchHoliday(PlatformData inData, PlatformData outData) throws Exception {
		List<HolidayBean> holidayList=datasetBeanMapper.datasetToBeans(inData, HolidayBean.class);
		circumstanceServiceFacade.batchHoliday(holidayList);
		//findHolidayListAll(inData,outData);
    }
	
	
/*
	// 모든 휴일목록을 가져오는 메서드 
	public void findHolidayListAll(PlatformData inData, PlatformData outData) throws Exception {
		List<HolidayBean> holidayList=circumstanceServiceFacade.findHolidayListAll();
		datasetBeanMapper.beansToDataset(outData, holidayList, HolidayBean.class);
    }
*/


   
}
