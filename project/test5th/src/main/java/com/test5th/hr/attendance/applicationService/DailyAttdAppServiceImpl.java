package com.test5th.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test5th.hr.attendance.dao.DailyAttdDAO;
import com.test5th.hr.attendance.to.ConditionBean;
import com.test5th.hr.attendance.to.DailyAttdBean;

@Component
public class DailyAttdAppServiceImpl implements DailyAttdAppService {
	/* DailyAttdDAO setting */
	@Autowired
	private DailyAttdDAO dailyAttdDAO;
	

	// 일근태목록을 가지고 오는 메서드 
	@Override
	public List<DailyAttdBean> findDailyAttdList(HashMap<String, String> map) {
	
		return dailyAttdDAO.selectDailyAttdList(map);
	}

	// 일근태를 추가하는 메서드 
	@Override
	public List<DailyAttdBean> addDailyAttd(DailyAttdBean dailyAttdBean) {
		
		dailyAttdDAO.insertDailyAttd(dailyAttdBean);
		
		HashMap<String,String> map=new HashMap<>();
		 map.put("empCode",dailyAttdBean.getEmpCode());
		 map.put("basicDay",dailyAttdBean.getBasicDay());
		 map.put("basicDay",dailyAttdBean.getBasicDay());
		
		return findDailyAttdList(map);
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
