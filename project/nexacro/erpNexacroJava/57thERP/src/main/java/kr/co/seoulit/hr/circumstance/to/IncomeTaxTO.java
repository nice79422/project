package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "dsIncomeTax")
public class IncomeTaxTO extends BaseTO {
	private String incomeTaxRate;
	private String inputedYear;
	private String lowSal;
	private String highSal;
	private String progressiveDeduction;

	@Column(name="INCOME_TAX_RATE")
	public String getIncomeTaxRate() {
		return incomeTaxRate;
	}

	@Column(name="INCOME_TAX_RATE")
	public void setIncomeTaxRate(String incomeTaxRate) {
		this.incomeTaxRate = incomeTaxRate;
	}
	@Column(name="INPUTED_YEAR")
	public String getInputedYear() {
		return inputedYear;
	}
	@Column(name="INPUTED_YEAR")
	public void setInputedYear(String inputedYear) {
		this.inputedYear = inputedYear;
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
	@Column(name="PROGRESSIVE_DEDUCTION")
	public String getProgressiveDeduction() {
		return progressiveDeduction;
	}
	@Column(name="PROGRESSIVE_DEDUCTION")
	public void setProgressiveDeduction(String progressiveDeduction) {
		this.progressiveDeduction = progressiveDeduction;
	}

	@Override
	public String toString() {
		return "IncomeTaxTO [incomeTaxRate=" + incomeTaxRate + ", inputedYear=" + inputedYear + ", lowSal=" + lowSal
				+ ", highSal=" + highSal + ", progressiveDeduction=" + progressiveDeduction + "]";
	}

}
