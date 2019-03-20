package kr.co.seoulit.hr.attendance.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.hr.attendance.to.DailyAttdRestTO;

public interface DailyAttdRestDAO {

	public List<DailyAttdRestTO> selectAttdRestList (HashMap<String,Object> map); 
	
	public void insertAttdRestList(DailyAttdRestTO dailyAttdRestTO);
	
	public void updateAttdRestList(DailyAttdRestTO dailyAttdRestTO);
	
	public void deleteAttdRestList(DailyAttdRestTO dailyAttdRestTO);
	
	public List<DailyAttdRestTO> selectAttdRestListByCondition(HashMap<String,Object> map);
	
}
