package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_payDeductionMgt")
public class PayDeductionItemTO extends BaseTO{
	
	private String payDeductionItemSeq;
	private String payTypeCode;
	private String payDeductionTypeCode;
	private String inputedYear;
	private String payDeductionItemCode;
	private String payDeductionItemName;
	private String taxTypeCode;
	private String susubApply;
	private String monthSal;
	
	
	public String getPayDeductionItemSeq() {
		return payDeductionItemSeq;
	}
	public void setPayDeductionItemSeq(String payDeductionItemSeq) {
		this.payDeductionItemSeq = payDeductionItemSeq;
	}
	public String getPayTypeCode() {
		return payTypeCode;
	}
	public void setPayTypeCode(String payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
	public String getPayDeductionTypeCode() {
		return payDeductionTypeCode;
	}
	public void setPayDeductionTypeCode(String payDeductionTypeCode) {
		this.payDeductionTypeCode = payDeductionTypeCode;
	}
	public String getInputedYear() {
		return inputedYear;
	}
	public void setInputedYear(String inputedYear) {
		this.inputedYear = inputedYear;
	}
	public String getPayDeductionItemCode() {
		return payDeductionItemCode;
	}
	public void setPayDeductionItemCode(String payDeductionItemCode) {
		this.payDeductionItemCode = payDeductionItemCode;
	}
	public String getPayDeductionItemName() {
		return payDeductionItemName;
	}
	public void setPayDeductionItemName(String payDeductionItemName) {
		this.payDeductionItemName = payDeductionItemName;
	}
	public String getTaxTypeCode() {
		return taxTypeCode;
	}
	public void setTaxTypeCode(String taxTypeCode) {
		this.taxTypeCode = taxTypeCode;
	}
	public String getSusubApply() {
		return susubApply;
	}
	public void setSusubApply(String susubApply) {
		this.susubApply = susubApply;
	}
	public String getMonthSal() {
		return monthSal;
	}
	public void setMonthSal(String monthSal) {
		this.monthSal = monthSal;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getInOutApply() {
		return inOutApply;
	}
	public void setInOutApply(String inOutApply) {
		this.inOutApply = inOutApply;
	}
	private String price;
	private String inOutApply;
}
