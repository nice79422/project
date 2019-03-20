package com.test4th.hr.attendance.applicationService;

import java.util.List;

import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DayAnnualBean;

public interface DayAnnualAppService {
	public List<DayAnnualBean> findAnnualMgt(String empCode,String standardYear);
	
	public List<DayAnnualBean> addDayAnnual(DayAnnualBean dayAnnulBean);
	
	public List<DayAnnualBean> findDayAnnualListByCondition(ConditionBean conditionBean);

	public void batchDayAnnual(List<DayAnnualBean> dayAnnualList);
	
}
