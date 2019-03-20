package com.test5th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DayAnnualBean;

public interface DayAnnualDAO {
	public List<DayAnnualBean> selectAnnualMgt(HashMap<String,String> map);
	
	public void insertDayAnnual(DayAnnualBean dayAnnualBean);
	
	public List<DayAnnualBean> selectDayAnnualListByCondition(ConditionBean conditionBean);
	
	public void updateDayAnnual(DayAnnualBean dayAnnualBean);
	
}
