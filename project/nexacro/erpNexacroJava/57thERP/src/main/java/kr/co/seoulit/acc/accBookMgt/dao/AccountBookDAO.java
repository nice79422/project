package kr.co.seoulit.acc.accBookMgt.dao;

import java.util.List;
import java.util.Map;

import kr.co.seoulit.acc.accBookMgt.to.AccountantTO;

public interface AccountBookDAO {
	public List<AccountantTO> selectAccountantList(Map<String,Object> map);
}
