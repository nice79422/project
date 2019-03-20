package kr.co.seoulit.statement.incomeStatement.applicationService;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import kr.co.seoulit.statement.incomeStatement.dao.EarlyIncomeStatementDAO;
import kr.co.seoulit.statement.incomeStatement.dao.IncomeStatementDAO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyIncomeStatementTO;
import kr.co.seoulit.statement.incomeStatement.to.EarlyrevenuesAndExpenseTO;
import kr.co.seoulit.statement.incomeStatement.to.IncomeStatementTO;


@Component
public class IncomeStatementApplicationServiceImpl implements IncomeStatementApplicationService {

	@Autowired
	private EarlyIncomeStatementDAO earlyIncomeStatementDAO;
	@Autowired
	private IncomeStatementDAO incomeStatementDAO;

	@Override
	public List<EarlyrevenuesAndExpenseTO> findRevenuesAndExpenseOnLastYear() {
		// TODO Auto-generated method stub
		List<EarlyrevenuesAndExpenseTO> EarlyrevenuesAndExpenseList = null;
		EarlyrevenuesAndExpenseList = earlyIncomeStatementDAO.selectRevenuesAndExpenseOnLastYear();
		return EarlyrevenuesAndExpenseList;
	}

	@Override
	public List<EarlyIncomeStatementTO> findIncomeStatementOnLastYear()  {
		// TODO Auto-generated method stub
		List<EarlyIncomeStatementTO> EarlyIncomeStatementList = null;
		EarlyIncomeStatementList = earlyIncomeStatementDAO.selectIncomeStatementOnLastYear();
		return EarlyIncomeStatementList;
	}

	@Override
	public List<IncomeStatementTO> findIncomeStatement(HashMap<String,String> approvalDateMap) {
		// TODO Auto-generated method stub
		List<IncomeStatementTO> incomeStatementList = null;
		incomeStatementList = incomeStatementDAO.selectIncomeStatement(approvalDateMap);
		return incomeStatementList;
	}


	
}
