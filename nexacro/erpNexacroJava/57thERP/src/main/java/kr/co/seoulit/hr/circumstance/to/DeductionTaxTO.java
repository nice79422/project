package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "dsDeductionTax")
public class DeductionTaxTO extends BaseTO {
	private String inputedYear;
	private String healthInsurRate;
	private String nationalPenRate;
	private String longTermCareRate;
	private String employeementInsurRate;

	@Column(name = "INPUTED_YEAR")
	public String getInputedYear() {
		return inputedYear;
	}

	@Column(name = "INPUTED_YEAR")
	public void setInputedYear(String inputedYear) {
		this.inputedYear = inputedYear;
	}

	@Column(name = "HEALTH_INSUR_RATE")
	public String getHealthInsurRate() {
		return healthInsurRate;
	}

	@Column(name = "HEALTH_INSUR_RATE")
	public void setHealthInsurRate(String healthInsurRate) {
		this.healthInsurRate = healthInsurRate;
	}

	@Column(name = "NATIONAL_PEN_RATE")
	public String getNationalPenRate() {
		return nationalPenRate;
	}

	@Column(name = "NATIONAL_PEN_RATE")
	public void setNationalPenRate(String nationalPenRate) {
		this.nationalPenRate = nationalPenRate;
	}

	@Column(name = "LONG_TERM_CARE_RATE")
	public String getLongTermCareRate() {
		return longTermCareRate;
	}

	@Column(name = "LONG_TERM_CARE_RATE")
	public void setLongTermCareRate(String longTermCareRate) {
		this.longTermCareRate = longTermCareRate;
	}

	@Column(name = "EMPLOYEEMENT_INSUR_RATE")
	public String getEmployeementInsurRate() {
		return employeementInsurRate;
	}

	@Column(name = "EMPLOYEEMENT_INSUR_RATE")
	public void setEmployeementInsurRate(String employeementInsurRate) {
		this.employeementInsurRate = employeementInsurRate;
	}

	@Override
	public String toString() {
		return "DeductionTaxTO [inputedYear=" + inputedYear + ", healthInsurRate=" + healthInsurRate
				+ ", nationalPenRate=" + nationalPenRate + ", longTermCareRate=" + longTermCareRate
				+ ", employeementInsurRate=" + employeementInsurRate + "]";
	}

}
