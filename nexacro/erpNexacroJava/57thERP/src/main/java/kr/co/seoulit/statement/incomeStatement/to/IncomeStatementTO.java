package kr.co.seoulit.statement.incomeStatement.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;


@Dataset(name="ds_IncomeStatement")
public class IncomeStatementTO extends BaseTO{
	private String displayName,
	currentPeriodLeftCol,
	currentPeriodRightCol,
	PreviousPeriodLeftCol,
	PreviousPeriodRightCol;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCurrentPeriodLeftCol() {
		return currentPeriodLeftCol;
	}

	public void setCurrentPeriodLeftCol(String currentPeriodLeftCol) {
		this.currentPeriodLeftCol = currentPeriodLeftCol;
	}

	public String getCurrentPeriodRightCol() {
		return currentPeriodRightCol;
	}

	public void setCurrentPeriodRightCol(String currentPeriodRightCol) {
		this.currentPeriodRightCol = currentPeriodRightCol;
	}

	public String getPreviousPeriodLeftCol() {
		return PreviousPeriodLeftCol;
	}

	public void setPreviousPeriodLeftCol(String previousPeriodLeftCol) {
		PreviousPeriodLeftCol = previousPeriodLeftCol;
	}

	public String getPreviousPeriodRightCol() {
		return PreviousPeriodRightCol;
	}

	public void setPreviousPeriodRightCol(String previousPeriodRightCol) {
		PreviousPeriodRightCol = previousPeriodRightCol;
	}
	
	

}
