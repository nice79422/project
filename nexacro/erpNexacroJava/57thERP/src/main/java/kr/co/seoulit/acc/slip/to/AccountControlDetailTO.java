package kr.co.seoulit.acc.slip.to;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_accountControlDetail")
public class AccountControlDetailTO extends BaseTO{
	private String accountCode,accountcontrolCode, accountcontrolName, accountcontrolType;

	@Column(name="ACCOUNT_CODE")
	public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	@Column(name="ACCOUNT_CONTROL_CODE")
	public String getAccountcontrolCode() {
		return accountcontrolCode;
	}

	public void setAccountcontrolCode(String accountcontrolCode) {
		this.accountcontrolCode = accountcontrolCode;
	}
	@Column(name="ACCOUNT_CONTROL_NAME")
	public String getAccountcontrolName() {
		return accountcontrolName;
	}

	public void setAccountcontrolName(String accountcontrolName) {
		this.accountcontrolName = accountcontrolName;
	}

	@Column(name="ACCOUNT_CONTROL_TYPE")
	public String getAccountcontrolType() {
		return accountcontrolType;
	}

	public void setAccountcontrolType(String accountcontrolType) {
		this.accountcontrolType = accountcontrolType;
	}

	
}
