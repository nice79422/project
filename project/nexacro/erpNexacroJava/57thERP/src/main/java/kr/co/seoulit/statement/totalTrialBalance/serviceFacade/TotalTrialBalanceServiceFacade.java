package kr.co.seoulit.statement.totalTrialBalance.serviceFacade;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.statement.totalTrialBalance.to.TotalTrialBalanceTO;


public interface TotalTrialBalanceServiceFacade {

	public List<TotalTrialBalanceTO> findTotalTrialBalanceList(HashMap<String,String> approvalDateMap);

}
