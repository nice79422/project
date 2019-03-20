package kr.co.seoulit.acc.accSubMgt.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "gds_parentAccountSummary")
public class ParentAccountSummaryTO extends BaseTO {
	private String accountInnerCode;
	private String accountName;      
    private List<AccountSummaryTO> accountList;
    
	public List<AccountSummaryTO> getAccountList() {
		return accountList;
	}
	public void setAccountList(List<AccountSummaryTO> accountList) {
		this.accountList = accountList;
	}
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	

	
}
