package kr.co.seoulit.statement.incomeStatement.serviceFacade;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import kr.co.seoulit.statement.incomeStatement.applicationService.IncomeStatementApplicationService;
import kr.co.seoulit.statement.incomeStatement.to.EarlyIncomeStatementTO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyrevenuesAndExpenseTO;
import kr.co.seoulit.statement.incomeStatement.to.IncomeStatementTO;


@Service
public class IncomeStatementServiceFacadeImpl implements IncomeStatementServiceFacade {

	@Autowired
	private IncomeStatementApplicationService incomeStatementApplicationService;
	

	@Override
	public List<EarlyrevenuesAndExpenseTO> findRevenuesAndExpenseOnLastYear()  {
		// TODO Auto-generated method stub
		List<EarlyrevenuesAndExpenseTO> earlyAccountCountList = null;
		earlyAccountCountList = incomeStatementApplicationService.findRevenuesAndExpenseOnLastYear();
		
		return earlyAccountCountList;
	}


	@Override
	public List<EarlyIncomeStatementTO> findIncomeStatementOnLastYear() {
		// TODO Auto-generated method stub
		List<EarlyIncomeStatementTO> earlyAccountCountList = null;
		earlyAccountCountList = incomeStatementApplicationService.findIncomeStatementOnLastYear();
		
		return earlyAccountCountList;
	}


	@Override
	public List<IncomeStatementTO> findIncomeStatement(HashMap<String,String> approvalDateMap)  {
		// TODO Auto-generated method stub
		List<IncomeStatementTO> incomeStatementList = null;
		incomeStatementList = incomeStatementApplicationService.findIncomeStatement(approvalDateMap);
		return incomeStatementList;
	}



}
