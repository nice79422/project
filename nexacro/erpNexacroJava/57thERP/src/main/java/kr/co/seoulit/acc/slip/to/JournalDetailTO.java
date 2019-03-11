package kr.co.seoulit.acc.slip.to;


import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_journalDetail")
public class JournalDetailTO extends BaseTO{
	private String journaldetailNo;
	private String journalNo;
	private String item;
	private String value;
	private String accountInnerCode;
	
	
	public String getAccountInnerCode() {
		return accountInnerCode;
	}
	public void setAccountInnerCode(String accountInnerCode) {
		this.accountInnerCode = accountInnerCode;
	}
	
	public String getJournaldetailNo() {
		return journaldetailNo;
	}
	public void setJournaldetailNo(String journaldetailNo) {
		this.journaldetailNo = journaldetailNo;
	}
	public String getJournalNo() {
		return journalNo;
	}
	public void setJournalNo(String journalNo) {
		this.journalNo = journalNo;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	

	}
