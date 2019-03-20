package com.test5th.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.hr.attendance.dao.DayAnnualDAO;
import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DayAnnualBean;

@Component
public class DayAnnualAppServiceImpl implements DayAnnualAppService {
	/* DayAnnualDAO setting */
	@Autowired
	private DayAnnualDAO dayAnnualDAO;

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	@Override
	public List<DayAnnualBean> findAnnualMgt(HashMap<String,String> map) {
		return dayAnnualDAO.selectAnnualMgt(map);
	}
	
	// 연차를 신청하는 메서드 
	@Override
	public List<DayAnnualBean> addDayAnnual(DayAnnualBean dayAnnualBean) {
		dayAnnualDAO.insertDayAnnual(dayAnnualBean);
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", dayAnnualBean.getEmpCode());
		map.put("standardYear", dayAnnualBean.getStandardYear());
		return findAnnualMgt(map);
	}
	
	// 연차 승인부분에서 연차를 조건에 따라 조회하는 메서드 
	@Override
	public List<DayAnnualBean> findDayAnnualListByCondition(ConditionBean conditionBean) {
		return dayAnnualDAO.selectDayAnnualListByCondition(conditionBean);
	}

	// 연차를 일괄적으로 처리하는 메서드 
	@Override
	public void batchDayAnnual(List<DayAnnualBean> dayAnnualList) {
		 for(DayAnnualBean dayAnnualBean : dayAnnualList) {    
	           dayAnnualDAO.updateDayAnnual(dayAnnualBean);
	      }
	}
	
}
