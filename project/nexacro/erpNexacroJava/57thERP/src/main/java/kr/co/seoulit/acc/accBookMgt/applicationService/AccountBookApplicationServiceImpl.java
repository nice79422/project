package kr.co.seoulit.acc.accBookMgt.applicationService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.co.seoulit.acc.accBookMgt.dao.AccountBookDAO;
import kr.co.seoulit.acc.accBookMgt.to.AccountantTO;

@Component
public class AccountBookApplicationServiceImpl implements AccountBookApplicationService {

	@Autowired
	private AccountBookDAO accountBookDAO;
	
	@Override
	public List<AccountantTO> findAccountantList(Map<String, Object> map) {
		return accountBookDAO.selectAccountantList(map);
	}

}
