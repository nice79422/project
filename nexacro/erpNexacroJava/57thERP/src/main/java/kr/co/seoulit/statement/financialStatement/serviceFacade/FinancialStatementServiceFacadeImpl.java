package kr.co.seoulit.statement.financialStatement.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.statement.financialStatement.applicationService.FinancialStatementApplicationService;
import kr.co.seoulit.statement.financialStatement.to.EarlyAssetsCountTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyFinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.EarlyLiabilitiesAndCapitalCountTO;
import kr.co.seoulit.statement.financialStatement.to.FinancialStatementsTO;
import kr.co.seoulit.statement.financialStatement.to.eFinancialStatementsTO;


@Service
public class FinancialStatementServiceFacadeImpl implements FinancialStatementServiceFacade {

	@Autowired
	private FinancialStatementApplicationService financialStatementApplicationService;
	
	
	@Override
	public List<FinancialStatementsTO> findFinancialStatements(HashMap<String, Object> approvalDate) {
		// TODO Auto-generated method stub
		List<FinancialStatementsTO> FinancialStatementsList = null;
		FinancialStatementsList = financialStatementApplicationService.findFinancialStatements(approvalDate);
		
		return FinancialStatementsList;
	}


	@Override
	public List<eFinancialStatementsTO> getEFinancialStatements(){
		// TODO Auto-generated method stub
		List<eFinancialStatementsTO> eFinancialStatementsList = null;
		eFinancialStatementsList = financialStatementApplicationService.getEFinancialStatements();
		
		return eFinancialStatementsList;
	}


	@Override
	public List<EarlyAssetsCountTO> findAssetsOnLastYear() {
		// TODO Auto-generated method stub
		List<EarlyAssetsCountTO> earlyAccountCountList = null;
		earlyAccountCountList = financialStatementApplicationService.findAssetsOnLastYear();
		
		return earlyAccountCountList;
	}

	@Override
	public List<EarlyLiabilitiesAndCapitalCountTO> findLiabilitiesAndCapitalOnLastYear()  {
		// TODO Auto-generated method stub
		List<EarlyLiabilitiesAndCapitalCountTO> earlyAccountCountList = null;
		earlyAccountCountList = financialStatementApplicationService.findLiabilitiesAndCapitalOnLastYear();
		
		return earlyAccountCountList;
	}


	@Override
	public List<EarlyFinancialStatementsTO> findFinancialStatementsOnLastYear()  {
		// TODO Auto-generated method stub
		List<EarlyFinancialStatementsTO> earlyAccountCountList = null;
		earlyAccountCountList = financialStatementApplicationService.findFinancialStatementsOnLastYear();
		
		return earlyAccountCountList;
	}

}
