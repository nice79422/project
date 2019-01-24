package com.test5th.hr.attendance.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.hr.attendance.dao.DailyAttdRestDAO;
import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdRestBean;

@Component
public class DailyAttdRestAppServiceImpl implements DailyAttdRestAppService {

	/* DailyAttdRestDAO setting */
	@Autowired
	private DailyAttdRestDAO dailyAttdRestDAO;
	

	
	// 근태외 목록을 가지고오는 메서드 
	@Override
	public List<DailyAttdRestBean> findAttdRestList(String empCode) {
		return dailyAttdRestDAO.selectAttdRestList(empCode);
	}
	
	
	// 근퇴외 신청을 하는 메서드 
	@Override
	public List<DailyAttdRestBean> addDailyAttdRest(DailyAttdRestBean dailyAttdRestBean) {
		dailyAttdRestDAO.insertDailyAttdRest(dailyAttdRestBean);
		return findAttdRestList(dailyAttdRestBean.getEmpCode());
	}
	
	
	// 근태승인관리, 일근태부분에서 조건에따라 근태외항목을 조회하기 위한 메서드 
	@Override
	public List<DailyAttdRestBean> findAttdRestListByCondition(ConditionBean conditionBean) {
		return  dailyAttdRestDAO.selectAttdRestListByCondition(conditionBean);
	}
	
	//일근태외 미승인 ->승인 후 목록 던짐 
	@Override
	public List<DailyAttdRestBean> findUnApprovedDailyAttdRestList(String basicDay) {
		return dailyAttdRestDAO.selectUnApprovedDailyAttdRestList(basicDay);
	}
		
	
	// 근태외항목을 일괄처리하기 위한 메서드 
	@Override
	public void batchDailyAttdRest(List<DailyAttdRestBean> dailyAttdRestList) {
		 for(DailyAttdRestBean dailyAttdRestBean : dailyAttdRestList) {
	         switch(dailyAttdRestBean.getStatus()) {
	            case "insert" : dailyAttdRestDAO.insertDailyAttdRest(dailyAttdRestBean); break;
	            case "update" : dailyAttdRestDAO.updateDailyAttdRest(dailyAttdRestBean); break;
	            case "delete" : dailyAttdRestDAO.deleteDailyAttdRest(dailyAttdRestBean); break;
	         }
	     }
	}

	
}
