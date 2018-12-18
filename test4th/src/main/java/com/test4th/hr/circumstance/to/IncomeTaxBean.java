package com.test4th.hr.circumstance.to;

import com.test4th.common.annotation.Column;
import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsIncomeTax")
public class IncomeTaxBean extends BaseBean{

	private String inputedYear;
	private String incomeTaxRate;
	private String lowSal;
	private String highSal;
	private String progressiveDeduction;

	@Column(name="PROGRESSIVE_DEDUCTION")
	public String getProgressiveDeduction() {
		return progressiveDeduction;
	}
	@Column(name="PROGRESSIVE_DEDUCTION")
	public void setProgressiveDeduction(String progressiveDeduction) {
		this.progressiveDeduction = progressiveDeduction;
	}

	@Column(name="INPUTED_YEAR")
	public String getInputedYear() {
		return inputedYear;
	}
	@Column(name="INPUTED_YEAR")
	public void setInputedYear(String inputedYear) {
		this.inputedYear = inputedYear;
	}

	@Column(name="INCOME_TAX_RATE")
	public String getIncomeTaxRate() {
		return incomeTaxRate;
	}
	@Column(name="INCOME_TAX_RATE")
	public void setIncomeTaxRate(String incomeTaxRate) {
		this.incomeTaxRate = incomeTaxRate;
	}

	@Column(name="LOW_SAL")
	public String getLowSal() {
		return lowSal;
	}
	@Column(name="LOW_SAL")
	public void setLowSal(String lowSal) {
		this.lowSal = lowSal;
	}

	@Column(name="HIGH_SAL")
	public String getHighSal() {
		return highSal;
	}
	@Column(name="HIGH_SAL")
	public void setHighSal(String highSal) {
		this.highSal = highSal;
	}

	@Override
	public String toString() {
		return "IncomeTaxBean [inputedYear=" + inputedYear + ", incomeTaxRate=" + incomeTaxRate + ", lowSal=" + lowSal
				+ ", highSal=" + highSal + "]";
	}
}
