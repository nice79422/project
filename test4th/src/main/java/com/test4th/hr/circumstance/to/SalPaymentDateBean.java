package com.test4th.hr.circumstance.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsSalPaymentDate")
public class SalPaymentDateBean extends BaseBean{

	private String paymentDate;
	private String inputedYearMonth;
	private String jikjongCode;
	private String targetChoice;
	private String sameTimeSelection;
	private String payType;
	private String salaryTypeCode;
	private String note;

	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getInputedYearMonth() {
		return inputedYearMonth;
	}
	public void setInputedYearMonth(String inputedYearMonth) {
		this.inputedYearMonth = inputedYearMonth;
	}
	public String getJikjongCode() {
		return jikjongCode;
	}
	public void setJikjongCode(String jikjongCode) {
		this.jikjongCode = jikjongCode;
	}
	public String getTargetChoice() {
		return targetChoice;
	}
	public void setTargetChoice(String targetChoice) {
		this.targetChoice = targetChoice;
	}
	public String getSameTimeSelection() {
		return sameTimeSelection;
	}
	public void setSameTimeSelection(String sameTimeSelection) {
		this.sameTimeSelection = sameTimeSelection;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getSalaryTypeCode() {
		return salaryTypeCode;
	}
	public void setSalaryTypeCode(String salaryTypeCode) {
		this.salaryTypeCode = salaryTypeCode;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
