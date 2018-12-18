package com.test4th.hr.circumstance.to;

import com.test4th.common.annotation.Column;
import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsDeductionTax")
public class DeductionTaxBean extends BaseBean{

	private String inputedYear;
	private String healthInsurRate;
	private String nationalPenRate;
	private String longTermCareRate;
	private String employeementInsurRate;

	@Column(name="INPUTED_YEAR")
	public String getInputedYear() {
		return inputedYear;
	}
	@Column(name="INPUTED_YEAR")
	public void setInputedYear(String inputedYear) {
		this.inputedYear = inputedYear;
	}

	@Column(name="HEALTH_INSUR_RATE")
	public String getHealthInsurRate() {
		return healthInsurRate;
	}
	@Column(name="HEALTH_INSUR_RATE")
	public void setHealthInsurRate(String healthInsurRate) {
		this.healthInsurRate = healthInsurRate;
	}

	@Column(name="NATIONAL_PEN_RATE")
	public String getNationalPenRate() {
		return nationalPenRate;
	}
	@Column(name="NATIONAL_PEN_RATE")
	public void setNationalPenRate(String nationalPenRate) {
		this.nationalPenRate = nationalPenRate;
	}

	@Column(name="LONG_TERM_CARE_RATE")
	public String getLongTermCareRate() {
		return longTermCareRate;
	}
	@Column(name="LONG_TERM_CARE_RATE")
	public void setLongTermCareRate(String longTermCareRate) {
		this.longTermCareRate = longTermCareRate;
	}

	@Column(name="EMPLOYEEMENT_INSUR_RATE")
	public String getEmployeementInsurRate() {
		return employeementInsurRate;
	}
	@Column(name="EMPLOYEEMENT_INSUR_RATE")
	public void setEmployeementInsurRate(String employeementInsurRate) {
		this.employeementInsurRate = employeementInsurRate;
	}

	@Override
	public String toString() {
		return "DeductionTaxBean [inputedYear=" + inputedYear + ", healthInsurRate=" + healthInsurRate
				+ ", nationalPenRate=" + nationalPenRate + ", longTermCareRate=" + longTermCareRate
				+ ", employeementInsurRate=" + employeementInsurRate + "]";
	}
}
