package kr.co.seoulit.hr.circumstance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.hr.attendance.to.DayAnnualTO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;

public interface HolidayDAO {
	
	public List<HolidayTO> selectHolidayList(HashMap<String, Object> dateList);
	
	public void insertHoliday(HolidayTO holidayTO);
	
	public void deleteHoliday(HolidayTO holidayTO);
	
	public void updateHoliday(HolidayTO holidayTO);
	
	public List<HolidayTO> selectHolidayListAll();
	
	public void insertHolidayInfo(DayAnnualTO dayAnnualTO);
	
	public Map<String, Object> addHoliday(HashMap<String, Object> map);
}
