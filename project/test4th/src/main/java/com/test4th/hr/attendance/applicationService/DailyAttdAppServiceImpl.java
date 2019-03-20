package com.test4th.hr.attendance.applicationService;

import java.util.List;

import com.test4th.hr.attendance.dao.DailyAttdDAO;
import com.test4th.hr.attendance.to.ConditionBean;
import com.test4th.hr.attendance.to.DailyAttdBean;

public class DailyAttdAppServiceImpl implements DailyAttdAppService {
	/* DailyAttdDAO setting */
	private DailyAttdDAO dailyAttdDAO;
	public void setDailyAttdDAO(DailyAttdDAO dailyAttdDAO) {
		this.dailyAttdDAO = dailyAttdDAO;
	}

	// 일근태목록을 가지고 오는 메서드 
	@Override
	public List<DailyAttdBean> findDailyAttdList(String empCode,String fromDate,String toDate) {
		return dailyAttdDAO.selectDailyAttdList(empCode,fromDate,toDate);
	}

	// 일근태를 추가하는 메서드 
	@Override
	public List<DailyAttdBean> addDailyAttd(DailyAttdBean dailyAttdBean) {
		dailyAttdDAO.insertDailyAttd(dailyAttdBean);
		return findDailyAttdList(dailyAttdBean.getEmpCode(),dailyAttdBean.getBasicDay(),dailyAttdBean.getBasicDay());
	}
	
	
	// 일근태 승인부분에서 일근태를 조건에 따라 조회하는 메서드 
	@Override
	public List<DailyAttdBean> findDailyAttdListByCondition(ConditionBean conditionBean) {
		return dailyAttdDAO.selectDailyAttdListByCondition(conditionBean);
	}
	
	//일근태 미승인을 승인으로 변경 시 (N이 없어짐 승은으로 변경 되었으면 값이 안나옴?)
	@Override
	public List<DailyAttdBean> findUnApprovedDailyAttdList(String basicDay) {
		return dailyAttdDAO.selectUnApprovedDailyAttdList(basicDay);
	}

	

	//일근태를 일괄적으로 처리하는 메서드 ( N -> Y 로 변경 하는 부분 )
	@Override
	public void batchDailyAttd(List<DailyAttdBean> dailyAttdList) {
		 for(DailyAttdBean dailyAttdBean : dailyAttdList) {
	            switch(dailyAttdBean.getStatus()) {
	            case "insert" : dailyAttdDAO.insertDailyAttd(dailyAttdBean); break;
	            case "update" : dailyAttdDAO.updateDailyAttd(dailyAttdBean); break; //일근태 관리 에서 미승인을 승인으로 변경 하면 여기 실행됨
	            case "delete" : dailyAttdDAO.deleteDailyAttd(dailyAttdBean); break;
	            }
	     }

	}

	

}
