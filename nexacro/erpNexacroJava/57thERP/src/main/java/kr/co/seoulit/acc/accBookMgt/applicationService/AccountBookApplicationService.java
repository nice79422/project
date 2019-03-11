package kr.co.seoulit.acc.accBookMgt.applicationService;

import java.util.List;
import java.util.Map;

import kr.co.seoulit.acc.accBookMgt.to.AccountantTO;

public interface AccountBookApplicationService {
	public List<AccountantTO> findAccountantList(Map<String, Object> map);
}
