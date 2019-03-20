package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;
import kr.co.seoulit.hr.circumstance.to.HolidayTO;
import kr.co.seoulit.hr.circumstance.to.PayStepTO;

public interface HolidayApplicationService {

	public List<HolidayTO> findHolidayList(HashMap<String, Object> dateList);
	public void batchHoliday(List<HolidayTO> holidayList);
	public List<HolidayTO> findHolidayListAll();
	public List<HolidayTO> addHoliday(HashMap<String, Object> map) throws ProcedureException;
}
