package kr.co.seoulit.acc.accSubMgt.to;


import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "gds_accountSummary")
public class AccountSummaryTO extends BaseTO {
	private String parentAccountInnerCode;
	private String accountInnerCode;
	private String accountName;      
    private String accountDescription;
    private String accountCharacter;
	public String getParentAccountInnerCode() {
		return parentAccountInnerCode;
	}
	public void setParentAccountInnerCode(String parentAccountInnerCode) {
		this.parentAccountInnerCode = parentAccountInnerCode;
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
	public String getAccountDescription() {
		return accountDescription;
	}
	public void setAccountDescription(String accountDescription) {
		this.accountDescription = accountDescription;
	}
	public String getAccountCharacter() {
		return accountCharacter;
	}
	public void setAccountCharacter(String accountCharacter) {
		this.accountCharacter = accountCharacter;
	}
 
    
	
}
