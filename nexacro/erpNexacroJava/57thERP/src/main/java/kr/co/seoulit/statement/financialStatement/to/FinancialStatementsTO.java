package kr.co.seoulit.statement.financialStatement.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_financialStatements")
public class FinancialStatementsTO extends BaseTO{
	private String accountName
	,currentLeft
	,currentRight
	,earlyLeft
	,earlyRight;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCurrentLeft() {
		return currentLeft;
	}

	public void setCurrentLeft(String currentLeft) {
		this.currentLeft = currentLeft;
	}

	public String getCurrentRight() {
		return currentRight;
	}

	public void setCurrentRight(String currentRight) {
		this.currentRight = currentRight;
	}

	public String getEarlyLeft() {
		return earlyLeft;
	}

	public void setEarlyLeft(String earlyLeft) {
		this.earlyLeft = earlyLeft;
	}

	public String getEarlyRight() {
		return earlyRight;
	}

	public void setEarlyRight(String earlyRight) {
		this.earlyRight = earlyRight;
	}



}
