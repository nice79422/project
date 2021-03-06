package com.test4th.hr.circumstance.applicationService;

import java.util.List;
import java.util.Map;

import com.test4th.hr.circumstance.dao.HolidayDAO;
import com.test4th.hr.circumstance.to.HolidayBean;

public class HolidayAppServiceImpl implements HolidayAppService {
	/* HolidayDAO setting */
    private HolidayDAO holidayDAO;
	public void setHolidayDAO(HolidayDAO holidayDAO) {
		this.holidayDAO = holidayDAO;
	}

	@Override
	/* 휴일목록을 가지고오는 메서드 */
	public List<HolidayBean> findHolidayList(Map<String, Object> dateList) {
		return holidayDAO.selectHolidayList(dateList);
	}

	@Override
	// 휴일관련 처리를 하는 메서드 
	public void batchHoliday(List<HolidayBean> holidayList) {
		for(HolidayBean holidayBean:holidayList){
			switch(holidayBean.getStatus()){
				case "insert" : holidayDAO.insertHoliday(holidayBean); break;
				
				case "delete" : holidayDAO.deleteHoliday(holidayBean); break;
				
				//case "update" : holidayDAO.updateHoliday(holidayBean); break;
			}
		}
	}

}
