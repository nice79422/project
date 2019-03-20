package kr.co.seoulit.statement.incomeStatement.dao;

import java.util.List;

import kr.co.seoulit.statement.incomeStatement.to.EarlyIncomeStatementTO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyrevenuesAndExpenseTO;


public interface EarlyIncomeStatementDAO {

	public List<EarlyrevenuesAndExpenseTO> selectRevenuesAndExpenseOnLastYear();
	public List<EarlyIncomeStatementTO> selectIncomeStatementOnLastYear();

}
