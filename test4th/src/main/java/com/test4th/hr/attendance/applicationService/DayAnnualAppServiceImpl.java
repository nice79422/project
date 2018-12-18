package com.test4th.hr.attendance.applicationService;

import java.util.List;

import com.test4th.hr.attendance.dao.DayAnnualDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DayAnnualBean;

public class DayAnnualAppServiceImpl implements DayAnnualAppService {
	/* DayAnnualDAO setting */
	private DayAnnualDAO dayAnnualDAO;
	public void setDayAnnualDAO(DayAnnualDAO dayAnnualDAO) {
		this.dayAnnualDAO = dayAnnualDAO;
	}

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	@Override
	public List<DayAnnualBean> findAnnualMgt(String empCode, String standardYear) {
		return dayAnnualDAO.selectAnnualMgt(empCode,standardYear);
	}
	
	// 연차를 신청하는 메서드 
	@Override
	public List<DayAnnualBean> addDayAnnual(DayAnnualBean dayAnnualBean) {
		dayAnnualDAO.insertDayAnnual(dayAnnualBean);
		return findAnnualMgt(dayAnnualBean.getEmpCode(),dayAnnualBean.getStandardYear());
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
