package kr.co.seoulit.statement.totalTrialBalance.applicationService;

import java.util.HashMap;
import java.util.List;


import kr.co.seoulit.statement.totalTrialBalance.to.TotalTrialBalanceTO;


public interface TotalTrialBalanceApplicationService {

	public List<TotalTrialBalanceTO> findTotalTrialBalanceList(HashMap<String,String> approvalDateMap);

	
}
