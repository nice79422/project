package com.test5th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdBean;

public interface DailyAttdDAO {
	//근태 목록 가져오기 
	public List<DailyAttdBean> selectDailyAttdList(HashMap<String, String> map);
	//근태 추가하기
	public void insertDailyAttd(DailyAttdBean dailyAttdBean);
	
	//미승인 일근태 내역 조회 
	public List<DailyAttdBean> selectDailyAttdListByCondition(ConditionBean conditionBean);
	
	// 일근태 미승인 -> 승인 변경  (N이 없어짐 승은으로 변경 되었으면 값이 안나옴?)
	public List<DailyAttdBean> selectUnApprovedDailyAttdList(String basicDay);
	
	//일근태 변동 사항 반영 
	public void updateDailyAttd(DailyAttdBean dailyAttdBean);
	
	public void deleteDailyAttd(DailyAttdBean dailyAttdBean);
}