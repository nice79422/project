package kr.co.seoulit.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.attendance.dao.DailyAttdDAO;
import kr.co.seoulit.hr.attendance.to.DailyAttdTO;

@Component
public class DailyAttdAppServiceImpl implements DailyAttdAppService{

	@Autowired
	private DailyAttdDAO dailyAttdDAO;
	
	@Override
	// 일근태목록을 가지고 오는 메서드 
	public List<DailyAttdTO> findDailyAttdList(HashMap<String, String> map){
		
		return dailyAttdDAO.selectDailyAttdList(map);
	}
	
	@Override
	// 근태신청한 내용을 추가하는 메서드
	public List<DailyAttdTO> registerDailyAttd(DailyAttdTO dailyAttdTO) {
		
		dailyAttdDAO.insertDailyAttdList(dailyAttdTO);
		
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode",dailyAttdTO.getEmpCode());
		map.put("basicDay",dailyAttdTO.getBasicDay());
		map.put("basicDay",dailyAttdTO.getBasicDay());
	
		return dailyAttdDAO.selectDailyAttdList(map);
	}
	
	// 일근태 목록을 수정,추가,삭제 하는 메서드
	@Override
	public void batchDailyAttd(List<DailyAttdTO> dailyAttdList) {
		
		 for(DailyAttdTO dailyAttdBean : dailyAttdList) {
	            switch(dailyAttdBean.getStatus()) {
	            case "inserted" : dailyAttdDAO.insertDailyAttdList(dailyAttdBean); break;
	            case "updated" : dailyAttdDAO.updateDailyAttdList(dailyAttdBean); break; 
	            case "deleted" : dailyAttdDAO.deleteDailyAttdList(dailyAttdBean); break;
	            }
	     }
	}

	// 일근태 승인부분에서 일근태를 조건에 따라 조회하는 메서드 
	@Override
	public List<DailyAttdTO> findDailyAttdListByCondition(HashMap<String,Object> map) {
		
		return dailyAttdDAO.selectDailyAttdListByCondition(map);
	}
}
