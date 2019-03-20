package kr.co.seoulit.acc.accSubMgt.dao;

import java.util.ArrayList;
import java.util.List;

import kr.co.seoulit.acc.accSubMgt.to.ParentAccountSummaryTO;
import kr.co.seoulit.acc.accSubMgt.to.AccountTO;


public interface AccountDAO {

	public List<ParentAccountSummaryTO> selectParentAccountList();
	public ArrayList<AccountTO> selectAllAccountList();

}
