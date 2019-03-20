package kr.co.seoulit.acc.accBookMgt.serviceFacade;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.seoulit.acc.accBookMgt.applicationService.AccountBookApplicationService;
import kr.co.seoulit.acc.accBookMgt.to.AccountantTO;

@Service
public class AccountBookServiceFacadeImpl implements AccountBookServiceFacade {

	@Autowired
	private AccountBookApplicationService accountBookApplicationService;

	@Override
	public List<AccountantTO> findAccountantList(Map<String, Object> map) {
		return accountBookApplicationService.findAccountantList(map);
	}

}
