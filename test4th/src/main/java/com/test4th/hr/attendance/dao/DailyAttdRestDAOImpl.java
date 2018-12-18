package com.test4th.hr.attendance.dao;

import java.util.List;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdRestBean;

public class DailyAttdRestDAOImpl extends IBatisDAO implements DailyAttdRestDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 근태외신청 내역을 조회하는 메서드*/
	public List<DailyAttdRestBean> selectAttdRestList(String empCode) {
		return getSqlMapClientTemplate().queryForList("dailyAttdRest.selectAttdRestList",empCode);
	}

	
	@SuppressWarnings("deprecation")
	@Override
	// 근태외신청을 한 내용을 추가하는 메서드 
	public void insertDailyAttdRest(DailyAttdRestBean dailyAttdRestBean) {
		getSqlMapClientTemplate().insert("dailyAttdRest.insertDailyAttdRest",dailyAttdRestBean);
	}
	
	
	//일근태관리에서  일 근태외 내역 조회 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdRestBean> selectAttdRestListByCondition(ConditionBean conditionBean) {
		return getSqlMapClientTemplate().queryForList("dailyAttdRest.selectAttdRestListByCondition", conditionBean);
	}
	
	//일근태외 미승인 -> 승인 변경 후 목록 던짐 
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<DailyAttdRestBean> selectUnApprovedDailyAttdRestList(String basicDay) {
		return getSqlMapClientTemplate().queryForList("dailyAttdRest.selectUnApprovedDailyAttdRestList", basicDay);
	}



	@SuppressWarnings("deprecation")
	@Override
	public void updateDailyAttdRest(DailyAttdRestBean dailyAttdRestBean) {
		getSqlMapClientTemplate().update("dailyAttdRest.updateDailyAttdRest",dailyAttdRestBean);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void deleteDailyAttdRest(DailyAttdRestBean dailyAttdRestBean) {
		getSqlMapClientTemplate().delete("dailyAttdRest.deleteDailyAttdRest",dailyAttdRestBean);
	}

	

}
