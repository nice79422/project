package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.attendance.to.DayAnnualTO;
import kr.co.seoulit.hr.circumstance.dao.HolidayDAO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;


@Component
public class HolidayApplicationServiceImpl implements HolidayApplicationService{

	@Autowired
	private HolidayDAO holidayDAO;

	@Override
	/* 휴일목록을 가지고오는 메서드 */
	public List<HolidayTO> findHolidayList(HashMap<String, Object> dateList) {
		return holidayDAO.selectHolidayList(dateList);
	}

	@Override
	// 휴일관련 처리를 하는 메서드 
	public void batchHoliday(List<HolidayTO> holidayList) {
		for(HolidayTO holidayTO:holidayList){
			System.out.println(holidayTO.getStatus());
			switch(holidayTO.getStatus()){
				case "inserted" :
					holidayDAO.insertHoliday(holidayTO); 
					break;
				case "updated" : 
					holidayDAO.updateHoliday(holidayTO); 
					break;				
				case "deleted" : 
					holidayDAO.deleteHoliday(holidayTO); 
					break;
			}
		}
	}

	@Override
	public List<HolidayTO> findHolidayListAll() {
		// TODO Auto-generated method stub
		return holidayDAO.selectHolidayListAll();
	}

	@Override
	public List<HolidayTO> addHoliday(HashMap<String, Object> map) throws ProcedureException {
		holidayDAO.addHoliday(map);
		int errorCode = Integer.parseInt((String) map.get("errorCode"));
		if (errorCode < 0) {
			throw new ProcedureException((String) map.get("errorMsg"));
		}
		// 이걸 넣어서 성공 메세지를 넣고 싶은데 안된다..
		// else if(errorCode == 0){
		// throw new ProcedureException((String) map.get("errorMsg"));
		// }
		return holidayDAO.selectHolidayListAll();
	}
	


}
