package kr.co.seoulit.acc.accBookMgt.to;

import kr.co.seoulit.common.annotation.Dataset;

@Dataset(name="ds_accountant")
public class AccountantTO {
	private String customerName;
	private String businessLicenseNumber;
	private String customerCeo;
	private String amount;
	private String leftDebtorPrice;
	private String rightCreditsPrice;
	private String balance;
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}
	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}
	public String getCustomerCeo() {
		return customerCeo;
	}
	public void setCustomerCeo(String customerCeo) {
		this.customerCeo = customerCeo;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getLeftDebtorPrice() {
		return leftDebtorPrice;
	}
	public void setLeftDebtorPrice(String leftDebtorPrice) {
		this.leftDebtorPrice = leftDebtorPrice;
	}
	public String getRightCreditsPrice() {
		return rightCreditsPrice;
	}
	public void setRightCreditsPrice(String rightCreditsPrice) {
		this.rightCreditsPrice = rightCreditsPrice;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}

	
}
