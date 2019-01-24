package com.test5th.hr.attendance.dao;

import java.util.List;

import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdRestBean;

public interface DailyAttdRestDAO {
	public List<DailyAttdRestBean> selectAttdRestList(String empCode);
	
	public void insertDailyAttdRest(DailyAttdRestBean dailyAttdRestBean);
	
	public List<DailyAttdRestBean> selectAttdRestListByCondition(ConditionBean conditionBean);
	
	public List<DailyAttdRestBean> selectUnApprovedDailyAttdRestList(String basicDay);

	public void updateDailyAttdRest(DailyAttdRestBean dailyAttdRestBean);
	public void deleteDailyAttdRest(DailyAttdRestBean dailyAttdRestBean);

}
