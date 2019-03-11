package kr.co.seoulit.hr.attendance.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.hr.attendance.dao.DayAnnualDAO;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;

@Component
public class DayAnnualApplicationServiceImpl implements DayAnnualApplicationService{

	
	/* DayAnnualDAO setting */
	@Autowired
	private DayAnnualDAO dayAnnualDAO;

	/* 해당년도, 해당사원의 연차정보, 사원정보를 조회하는 메서드 */
	@Override
	public List<DayAnnualTO> findAnnualMgt(HashMap<String,String> map) {
		return dayAnnualDAO.selectAnnualMgt(map);
	}	
	
	// 연차를 신청하는 메서드 
	@Override
	public List<DayAnnualTO> addDayAnnual(DayAnnualTO dayAnnualTO) {
		dayAnnualDAO.insertDayAnnual(dayAnnualTO);
		HashMap<String,String> map=new HashMap<>();
		map.put("empCode", dayAnnualTO.getEmpCode());
		map.put("standardYear", dayAnnualTO.getStandardYear());
		return findAnnualMgt(map);
	}

	@Override
	public List<DayAnnualTO> findDayAnnualListByCondition(HashMap<String, Object> map) {
		
		return dayAnnualDAO.selectDayAnnualListByCondition(map);
	}

	@Override
	public void batchDayAnnual(List<DayAnnualTO> dayAnnualList) {
		
		for(DayAnnualTO dayAnnualBean : dayAnnualList){
			switch(dayAnnualBean.getStatus()) {
			
			case "inserted" : dayAnnualDAO.insertDayAnnual(dayAnnualBean);break;
			case "updated" : dayAnnualDAO.updateDayAnnual(dayAnnualBean);break;
			case "deleted" :	 dayAnnualDAO.deleteDayAnnual(dayAnnualBean);break;
			}
		}
	}
}
