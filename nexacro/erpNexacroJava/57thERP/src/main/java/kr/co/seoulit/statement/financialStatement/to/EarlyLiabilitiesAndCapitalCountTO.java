package kr.co.seoulit.statement.financialStatement.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;



@Dataset(name="ds_earlyLiabilitiesAndCapitalCount")
public class EarlyLiabilitiesAndCapitalCountTO extends BaseTO{
	private String groupCode,
	accountInnerCode,
	accountName,
	price;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	

}
