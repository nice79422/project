package kr.co.seoulit.statement.incomeStatement.applicationService;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.statement.incomeStatement.to.EarlyIncomeStatementTO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyrevenuesAndExpenseTO;
import kr.co.seoulit.statement.incomeStatement.to.IncomeStatementTO;


public interface IncomeStatementApplicationService {

	public List<EarlyrevenuesAndExpenseTO> findRevenuesAndExpenseOnLastYear();
	public List<EarlyIncomeStatementTO> findIncomeStatementOnLastYear();
	public List<IncomeStatementTO> findIncomeStatement(HashMap<String,String> approvalDateMap);

}

