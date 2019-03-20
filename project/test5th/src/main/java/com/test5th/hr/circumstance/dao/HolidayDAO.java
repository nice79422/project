package com.test5th.hr.circumstance.dao;

import java.util.List;
import java.util.Map;

import com.test5th.hr.circumstance.to.HolidayBean;

public interface HolidayDAO {
	public List<HolidayBean> selectHolidayList(Map<String, Object> dateList);
	
	public void insertHoliday(HolidayBean holidayBean);
	public void deleteHoliday(HolidayBean holidayBean);
	
	//public void updateHoliday(HolidayBean holidayBean);
}
