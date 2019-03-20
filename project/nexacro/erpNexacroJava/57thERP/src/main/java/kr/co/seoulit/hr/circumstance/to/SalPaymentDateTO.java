package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_salPaymentDate")
public class SalPaymentDateTO extends BaseTO{

	private String paymentDate;
	private String inputedYearMonth;
	private String jikjongCode;
	private String targetChoice;
	private String sameTimeSelection;
	private String payType;
	private String salaryTypeCode;
	private String note;
	private String slipRegistStatus;

	public String getSlipRegistStatus() {
		return slipRegistStatus;
	}
	public void setSlipRegistStatus(String slipRegistStatus) {
		this.slipRegistStatus = slipRegistStatus;
	}
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
