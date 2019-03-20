package kr.co.seoulit.acc.accSubMgt.dao;

import java.util.List;

import kr.co.seoulit.acc.accSubMgt.to.AccountSummaryTO;


public interface AccountSummaryDAO {

	public List<AccountSummaryTO> selectAccountSummaryList();
	public void insertAccountSummary(AccountSummaryTO accountSummaryTO);
	public void deleteAccountSummary(AccountSummaryTO accountSummaryTO);

}
