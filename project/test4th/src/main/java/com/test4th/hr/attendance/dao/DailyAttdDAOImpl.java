package com.test4th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdBean;

public class DailyAttdDAOImpl extends IBatisDAO implements DailyAttdDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 일근태목록을 가지고 오는 메서드 */
	public List<DailyAttdBean> selectDailyAttdList(String empCode,String fromDate,String toDate) {
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", empCode);
		map.put("fromDate", fromDate);
		map.put("toDate", toDate);
		return getSqlMapClientTemplate().queryForList("dailyAttd.selectDailyAttdList",map);
	}

	
	@SuppressWarnings("deprecation")
	@Override
	// 일근태를 추가하는 메서드 
	public void insertDailyAttd(DailyAttdBean dailyAttdBean) {
		getSqlMapClientTemplate().insert("dailyAttd.insertDailyAttd",dailyAttdBean);
	}
	
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	// 일근태승인관리화면에서 일근태를 조건에 따라 조회하는 메서드
	public List<DailyAttdBean> selectDailyAttdListByCondition(ConditionBean conditionBean) {
		return getSqlMapClientTemplate().queryForList("dailyAttd.selectDailyAttdListByCondition",conditionBean);
	}

	
	// 일근태 내역 미승인 -> 승인 변경 된 내역 을 조회 ?? N이 없어짐 승은으로 변경 되었으면 값이 안나옴?
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdBean> selectUnApprovedDailyAttdList(String basicDay) {

		return getSqlMapClientTemplate().queryForList("dailyAttd.selectUnApprovedDailyAttdList",basicDay);
	}
	

	// 일근태승인화면에서 일근태를 승인처리한다고 수정하는 메서드 (미승인이 승인으로 N -> Y)
	@SuppressWarnings("deprecation")
	@Override
	public void updateDailyAttd(DailyAttdBean dailyAttdBean) {
		getSqlMapClientTemplate().update("dailyAttd.updateDailyAttd",dailyAttdBean);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDailyAttd(DailyAttdBean dailyAttdBean) {
		getSqlMapClientTemplate().insert("dailyAttd.deleteDailyAttd",dailyAttdBean);

	}



}
