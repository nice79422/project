package kr.co.seoulit.statement.financialStatement.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.statement.financialStatement.dao.EarlyFinancialStatementsDAO;
import kr.co.seoulit.statement.financialStatement.dao.FinancialStatementsDAO;
import kr.co.seoulit.statement.financialStatement.to.EarlyAssetsCountTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyFinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyLiabilitiesAndCapitalCountTO;
import kr.co.seoulit.statement.financialStatement.to.FinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.eFinancialStatementsTO;

@Component
public class FinancialStatementApplicationServiceImpl implements FinancialStatementApplicationService {


	@Autowired
	private FinancialStatementsDAO financialStatementsDAO;
	@Autowired
	private EarlyFinancialStatementsDAO earlyFinancialStatementsDAO;


	@Override
	public List<FinancialStatementsTO> findFinancialStatements(HashMap<String, Object> approvalDate) {
		// TODO Auto-generated method stub
		List<FinancialStatementsTO> FinancialStatementsList = null;
		FinancialStatementsList = financialStatementsDAO.selectFinancialStatements(approvalDate);
		return FinancialStatementsList;
	}

	@Override
	public List<eFinancialStatementsTO> getEFinancialStatements(){
		// TODO Auto-generated method stub
		List<eFinancialStatementsTO> eFinancialStatementsList = null;
		eFinancialStatementsList = financialStatementsDAO.selectEFinancialStatements();
		return eFinancialStatementsList;
	}

	@Override
	public List<EarlyAssetsCountTO> findAssetsOnLastYear() {
		// TODO Auto-generated method stub
		List<EarlyAssetsCountTO> earlyAccountCountList = null;
		earlyAccountCountList = earlyFinancialStatementsDAO.selectAssetsOnLastYear();
		return earlyAccountCountList;
	}

	@Override
	public List<EarlyLiabilitiesAndCapitalCountTO> findLiabilitiesAndCapitalOnLastYear() {
		// TODO Auto-generated method stub
		List<EarlyLiabilitiesAndCapitalCountTO> earlyAccountCountList = null;
		earlyAccountCountList = earlyFinancialStatementsDAO.selectLiabilitiesAndCapitalOnLastYear();
		return earlyAccountCountList;
	}

	@Override
	public List<EarlyFinancialStatementsTO> findFinancialStatementsOnLastYear(){
		// TODO Auto-generated method stub
		List<EarlyFinancialStatementsTO> earlyFinancialStatementsList = null;
		earlyFinancialStatementsList = earlyFinancialStatementsDAO.selectFinancialStatementsOnLastYear();
		return earlyFinancialStatementsList;
	}
	
}
