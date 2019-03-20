package kr.co.seoulit.statement.financialStatement.dao;

import java.util.List;

import kr.co.seoulit.statement.financialStatement.to.EarlyAssetsCountTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyFinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyLiabilitiesAndCapitalCountTO;


public interface EarlyFinancialStatementsDAO {
	
	public List<EarlyAssetsCountTO> selectAssetsOnLastYear();
	public List<EarlyLiabilitiesAndCapitalCountTO> selectLiabilitiesAndCapitalOnLastYear();
	public List<EarlyFinancialStatementsTO> selectFinancialStatementsOnLastYear();

	
}
