package kr.co.seoulit.hr.circumstance.applicationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.seoulit.common.exception.ProcedureException;
import kr.co.seoulit.hr.circumstance.to.AnnualLeaveTO;

public interface AnnualLeaveApplicationService {

	public List<AnnualLeaveTO> findAnnualLeaveList();
	
	public List<AnnualLeaveTO> createAnnualLeave(HashMap<String, Object> map) throws ProcedureException;
	
	public void batchAnnualLeave(List<AnnualLeaveTO> annualList);
	
	public List<AnnualLeaveTO> editAnnualLeaveMgt(HashMap<String, Object> map);
}
