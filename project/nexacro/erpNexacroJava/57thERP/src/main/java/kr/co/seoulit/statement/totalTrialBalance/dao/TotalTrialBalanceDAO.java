package kr.co.seoulit.statement.totalTrialBalance.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.statement.totalTrialBalance.to.TotalTrialBalanceTO;

public interface TotalTrialBalanceDAO {

	public List<TotalTrialBalanceTO> selectTotalTrialBalanceList(HashMap<String,String> approvalDateMap);
}
