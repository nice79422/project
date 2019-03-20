package com.test4th.hr.attendance.dao;

import java.util.List;

import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DayAnnualBean;

public interface DayAnnualDAO {
	public List<DayAnnualBean> selectAnnualMgt(String empCode,String standardDate);
	
	public void insertDayAnnual(DayAnnualBean dayAnnualBean);
	
	public List<DayAnnualBean> selectDayAnnualListByCondition(ConditionBean conditionBean);
	
	public void updateDayAnnual(DayAnnualBean dayAnnualBean);
	
}
