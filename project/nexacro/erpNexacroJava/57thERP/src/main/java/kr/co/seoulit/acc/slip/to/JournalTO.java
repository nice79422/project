package kr.co.seoulit.acc.slip.to;

import java.util.List;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.annotation.Remove;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_journal")
public class JournalTO extends BaseTO {

	private String journalNo;
	private String slipNo;
	private String balanceDivision;
	private String accountInnerCode;
	private String accountName;
	private String summaryNumber;
	private String summaryComment;
	private String customerCode;
	private String customerName;
	private String leftDebtorPrice;
	private String rightCreditsPrice;
	private String journalDescription;
	private String slipApprovalDate;
	private String price;
	private List<JournalDetailTO> journalDetailToList;
	
	@Remove
	public List<JournalDetailTO> getJournalDetailToList() {
		return journalDetailToList;
	}
	public void setJournalDetailToList(List<JournalDetailTO> journalDetailToList) {
		this.journalDetailToList = journalDetailToList;
	}
	@Column(name="SLIP_APPROVAL_DATE")
	public String getSlipApprovalDate() {
		return slipApprovalDate;
	}
	@Column(name="SLIP_APPROVAL_DATE")
	public void setSlipApprovalDate(String slipApprovalDate) {
		this.slipApprovalDate = slipApprovalDate;
	}
	@Column(name="JOURNAL_NO")
	public String getJournalNo() {
		return journalNo;
	}
	@Column(name="JOURNAL_NO")
	public void setJournalNo(String journalNo) {
		this.journalNo = journalNo;
	}
	@Column(name="SLIP_NO")
	public String getSlipNo() {
		return slipNo;
	}
	@Column(name="SLIP_NO")
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	@Column(name="BALANCE_DIVISION")
	public String getBalanceDivision() {
		return balanceDivision;
	}
	@Column(name="BALANCE_DIVISION")
	public void setBalanceDivision(String balanceDivision) {
		this.balanceDivision = balanceDivision;
	}
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	@Column(name="ACCOUNT_NAME")
	public String getAccountName() {
		return accountName;
	}
	@Column(name="ACCOUNT_NAME")
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	@Column(name="SUMMARY_NUMBER")
	public String getSummaryNumber() {
		return summaryNumber;
	}
	@Column(name="SUMMARY_NUMBER")
	public void setSummaryNumber(String summaryNumber) {
		this.summaryNumber = summaryNumber;
	}
	@Column(name="SUMMARY_COMMENT")
	public String getSummaryComment() {
		return summaryComment;
	}
	@Column(name="SUMMARY_COMMENT")
	public void setSummaryComment(String summaryComment) {
		this.summaryComment = summaryComment;
	}
	@Column(name="CUSTOMER_CODE")
	public String getCustomerCode() {
		return customerCode;
	}
	@Column(name="CUSTOMER_CODE")
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}
	@Column(name="CUSTOMER_NAME")
	public String getCustomerName() {
		return customerName;
	}
	@Column(name="CUSTOMER_NAME")
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Column(name="LEFT_DEBTOR_PRICE")
	public String getLeftDebtorPrice() {
		return leftDebtorPrice;
	}
	@Column(name="LEFT_DEBTOR_PRICE")
	public void setLeftDebtorPrice(String leftDebtorPrice) {
		this.leftDebtorPrice = leftDebtorPrice;
	}
	@Column(name="RIGHT_CREDITS_PRICE")
	public String getRightCreditsPrice() {
		return rightCreditsPrice;
	}
	@Column(name="RIGHT_CREDITS_PRICE")
	public void setRightCreditsPrice(String rightCreditsPrice) {
		this.rightCreditsPrice = rightCreditsPrice;
	}
	@Column(name="JOURNAL_DESCRIPTION")
	public String getJournalDescription() {
		return journalDescription;
	}
	@Column(name="JOURNAL_DESCRIPTION")
	public void setJournalDescription(String journalDescription) {
		this.journalDescription = journalDescription;
	}
	@Column(name="PRICE")
	public String getPrice() {
		return price;
	}
	@Column(name="PRICE")
	public void setPrice(String price) {
		this.price = price;
	}

}
