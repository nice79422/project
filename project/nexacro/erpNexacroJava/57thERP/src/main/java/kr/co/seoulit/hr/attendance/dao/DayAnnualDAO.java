package kr.co.seoulit.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.attendance.to.DayAnnualTO;

public interface DayAnnualDAO {
	
	public List<DayAnnualTO> selectAnnualMgt(HashMap<String,String> map);
	
	public void insertDayAnnual(DayAnnualTO dayAnnualTO);
	
	public void updateDayAnnual(DayAnnualTO dayAnnualTO);
	
	public void deleteDayAnnual(DayAnnualTO dayAnnualTO);
	
	public List<DayAnnualTO> selectDayAnnualListByCondition(HashMap<String, Object> map);
}
