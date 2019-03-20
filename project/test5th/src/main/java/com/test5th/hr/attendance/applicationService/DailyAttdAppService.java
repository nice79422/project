package com.test5th.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdBean;

public interface DailyAttdAppService {
	public List<DailyAttdBean> findDailyAttdList(HashMap<String, String> map);	

	public List<DailyAttdBean> addDailyAttd(DailyAttdBean dailyAttdBean);
	
	public List<DailyAttdBean> findDailyAttdListByCondition(ConditionBean conditionBean);
	
	public List<DailyAttdBean> findUnApprovedDailyAttdList(String basicDay);
	
	
	public void batchDailyAttd(List<DailyAttdBean> dailyAttdList);
	
	
}
