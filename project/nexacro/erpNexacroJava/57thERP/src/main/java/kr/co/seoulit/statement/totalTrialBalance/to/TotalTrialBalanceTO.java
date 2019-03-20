package kr.co.seoulit.statement.totalTrialBalance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;


@Dataset(name="ds_totalTrialBalance")
public class TotalTrialBalanceTO extends BaseTO{
	 		private String 
	 		leftDebtorBalance,
	 		leftDebtorSum,
	 		accountName,
	 		rightCreditsSum,
	 		rightCreditsBalance
			;

			public String getLeftDebtorBalance() {
				return leftDebtorBalance;
			}

			public void setLeftDebtorBalance(String leftDebtorBalance) {
				this.leftDebtorBalance = leftDebtorBalance;
			}

			public String getLeftDebtorSum() {
				return leftDebtorSum;
			}

			public void setLeftDebtorSum(String leftDebtorSum) {
				this.leftDebtorSum = leftDebtorSum;
			}

			public String getAccountName() {
				return accountName;
			}

			public void setAccountName(String accountName) {
				this.accountName = accountName;
			}

			public String getRightCreditsSum() {
				return rightCreditsSum;
			}

			public void setRightCreditsSum(String rightCreditsSum) {
				this.rightCreditsSum = rightCreditsSum;
			}

			public String getRightCreditsBalance() {
				return rightCreditsBalance;
			}

			public void setRightCreditsBalance(String rightCreditsBalance) {
				this.rightCreditsBalance = rightCreditsBalance;
			}
	 		
		
			
	 		
}
