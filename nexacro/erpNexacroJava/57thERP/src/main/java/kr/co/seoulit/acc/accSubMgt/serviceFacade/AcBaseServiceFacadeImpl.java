package kr.co.seoulit.acc.accSubMgt.serviceFacade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.acc.accSubMgt.applicationService.AcBaseApplicationService;
import kr.co.seoulit.acc.accSubMgt.to.ParentAccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountTO;


@Service
public class AcBaseServiceFacadeImpl implements AcBaseServiceFacade{
	
	@Autowired
	private AcBaseApplicationService acBaseApplicationService;

	
	
	@Override
	public List<ParentAccountSummaryTO> findParentAccountList() {
		// TODO Auto-generated method stub
		List<ParentAccountSummaryTO> accountSummaryList = acBaseApplicationService.findParentAccountList();
		return accountSummaryList;
	}
	
	
	@Override	
	public List<AccountTO> findAccountList() {
		// TODO Auto-generated method stub
		List<AccountTO> accountList = acBaseApplicationService.findAccountList();
		return accountList;
	}


	@Override
	public List<AccountSummaryTO> findAccountSummaryList() {
		// TODO Auto-generated method stub
		List<AccountSummaryTO> accountSummaryList = acBaseApplicationService.findAccountSummaryList();
		return accountSummaryList;
	}


	@Override
	public void modifyAccountSummaryList(List<AccountSummaryTO> accountSummaryList) {
		// TODO Auto-generated method stub
		acBaseApplicationService.modifyAccountSummaryList(accountSummaryList);		
	}






}
