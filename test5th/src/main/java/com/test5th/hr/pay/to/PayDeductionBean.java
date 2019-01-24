package com.test5th.hr.pay.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsPayDeduction")
public class PayDeductionBean extends BaseBean{

	private String payDeductionSeq;
	private String price;
	private String payDeductionItemCode;
	private String payDeductionItemName;
	private String empCode;
	private String paymentDate;

	
	public String getPayDeductionItemName() {
		return payDeductionItemName;
	}
	
	public String getPayDeductionSeq() {
		return payDeductionSeq;
	}
	public void setPayDeductionSeq(String payDeductionSeq) {
		this.payDeductionSeq = payDeductionSeq;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getPayDeductionItemCode() {
		return payDeductionItemCode;
	}
	public void setPayDeductionItemCode(String payDeductionItemCode) {
		this.payDeductionItemCode = payDeductionItemCode;
	}
	public String getPayDeductionsItemName() {
		return payDeductionItemName;
	}
	public void setPayDeductionItemName(String payDeductionItemName) {
		this.payDeductionItemName = payDeductionItemName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}
