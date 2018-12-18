package com.test4th.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test4th.common.dao.IBatisDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DayAnnualBean;

public class DayAnnualDAOImpl extends IBatisDAO implements DayAnnualDAO {

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	public List<DayAnnualBean> selectAnnualMgt(String empCode,String standardYear) {
		Map<String, Object> map = new HashMap<>();
        map.put("empCode", empCode);
        map.put("standardYear", standardYear);
		return getSqlMapClientTemplate().queryForList("dayAnnual.selectAnnualMgt",map);
	}
	
	// 연차를 신청하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void insertDayAnnual(DayAnnualBean dayAnnualBean) {
		getSqlMapClientTemplate().insert("dayAnnual.insertDayAnnual",dayAnnualBean);
	}
	
	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	// 일근태승인관리화면에서 일근태를 조건에 따라 조회하는 메서드
	public List<DayAnnualBean> selectDayAnnualListByCondition(ConditionBean conditionBean) {
		return getSqlMapClientTemplate().queryForList("dayAnnual.selectDayAnnualListByCondition",conditionBean);
	}

	// 일근태승인화면에서 일근태를 승인처리한다고 수정하는 메서드 
	@SuppressWarnings("deprecation")
	@Override
	public void updateDayAnnual(DayAnnualBean dayAnnualBean) {
		getSqlMapClientTemplate().update("dayAnnual.updateDayAnnual", dayAnnualBean);

	}
	
}
