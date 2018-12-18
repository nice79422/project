package com.test4th.hr.attendance.applicationService;

import java.util.List;

import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdRestBean;

public interface DailyAttdRestAppService {
	public List<DailyAttdRestBean> findAttdRestList(String empCode);
	
	public List<DailyAttdRestBean> addDailyAttdRest(DailyAttdRestBean dailyAttdRestBean);
	
	public List<DailyAttdRestBean> findAttdRestListByCondition(ConditionBean conditionBean);	
	
	public List<DailyAttdRestBean> findUnApprovedDailyAttdRestList(String basicDay);
	
	public void batchDailyAttdRest(List<DailyAttdRestBean> dailyAttdRestList);
	
	
	
}
