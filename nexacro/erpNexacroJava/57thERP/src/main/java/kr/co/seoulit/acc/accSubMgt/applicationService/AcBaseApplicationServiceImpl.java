package kr.co.seoulit.acc.accSubMgt.applicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.accSubMgt.dao.AccountDAO;
import kr.co.seoulit.acc.accSubMgt.dao.AccountSummaryDAO;
import kr.co.seoulit.acc.accSubMgt.to.ParentAccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountTO;


@Component
public class AcBaseApplicationServiceImpl implements AcBaseApplicationService {

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private AccountSummaryDAO accountSummaryDAO;



	@Override
	public List<ParentAccountSummaryTO> findParentAccountList() {
		// TODO Auto-generated method stub
		List<ParentAccountSummaryTO> accountSummaryList = accountDAO.selectParentAccountList();
		return accountSummaryList;
	}


	@Override
	public List<AccountTO> findAccountList() {
		// TODO Auto-generated method stub
		List<AccountTO> accountList = accountDAO.selectAllAccountList();

		return accountList;
	}

	@Override
	public List<AccountSummaryTO> findAccountSummaryList() {
		// TODO Auto-generated method stub
		List<AccountSummaryTO> accountSummaryList = accountSummaryDAO.selectAccountSummaryList();

		return accountSummaryList;
	}

	@Override
	public void modifyAccountSummaryList(List<AccountSummaryTO> accountSummaryList) {
		// TODO Auto-generated method stub
		for(AccountSummaryTO accountSummaryTO: accountSummaryList) {
			System.out.println("accountSummaryTO : "+accountSummaryTO.getAccountInnerCode());
			System.out.println("accountSummaryTO : "+accountSummaryTO.getAccountDescription());
		switch(accountSummaryTO.getStatus().toString()) {
		case "insert" :
			accountSummaryDAO.insertAccountSummary(accountSummaryTO);
			break;
		case "delete" :
			accountSummaryDAO.deleteAccountSummary(accountSummaryTO);
			break;
			}
		}

	}



}
