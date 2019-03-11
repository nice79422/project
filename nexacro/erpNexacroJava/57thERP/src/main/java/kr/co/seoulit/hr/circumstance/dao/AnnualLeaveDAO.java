package kr.co.seoulit.hr.circumstance.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;

public interface AnnualLeaveDAO {

	public List<AnnualLeaveTO> selectAnnualLeaveList();
	
	public Map<String, Object> createAnnualLeave(HashMap<String, Object> map);
	
	public void insertAnnualLeave(AnnualLeaveTO annualLeaveTO);
	
	public void updateAnnualLeave(AnnualLeaveTO annualLeaveTO);
	
	public void deleteAnnualLeave(AnnualLeaveTO annualLeaveTO);	
	
	public void updateAnnualLeaveMgt(HashMap<String, Object> map);
}
