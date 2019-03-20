package kr.co.seoulit.statement.financialStatement.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.seoulit.statement.financialStatement.to.FinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.eFinancialStatementsTO;


public interface FinancialStatementsDAO {

	public List<FinancialStatementsTO> selectFinancialStatements(HashMap<String, Object> approvalDate);
	public List<eFinancialStatementsTO> selectEFinancialStatements();
	
}
