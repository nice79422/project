package kr.co.seoulit.hr.pm.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="dsSalInfo")
public class SalInfoTO extends BaseTO{
	
	private String empCode;
	private String hobong;
	private String salaryTypeCode;
	private String pensionType;
	private String nationalPension;
	private String healthInsurance;
	private String employeementInsurance;
	private String longTermCareInsurance;
	private String supportTwentyBelow;
	private String supportSixtyAbove;
	private String manyChildDeduction;
	private String accountCode;
	private String accountHolder;
	private String salTransBank;

	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getHobong() {
		return hobong;
	}
	public void setHobong(String hobong) {
		this.hobong = hobong;
	}
	public String getSalaryTypeCode() {
		return salaryTypeCode;
	}
	public void setSalaryTypeCode(String salaryTypeCode) {
		this.salaryTypeCode = salaryTypeCode;
	}
	public String getPensionType() {
		return pensionType;
	}
	public void setPensionType(String pensionType) {
		this.pensionType = pensionType;
	}
	public String getNationalPension() {
		return nationalPension;
	}
	public void setNationalPension(String nationalPension) {
		this.nationalPension = nationalPension;
	}
	public String getHealthInsurance() {
		return healthInsurance;
	}
	public void setHealthInsurance(String healthInsurance) {
		this.healthInsurance = healthInsurance;
	}
	public String getEmployeementInsurance() {
		return employeementInsurance;
	}
	public void setEmployeementInsurance(String employeementInsurance) {
		this.employeementInsurance = employeementInsurance;
	}
	public String getLongTermCareInsurance() {
		return longTermCareInsurance;
	}
	public void setLongTermCareInsurance(String longTermCareInsurance) {
		this.longTermCareInsurance = longTermCareInsurance;
	}
	public String getSupportTwentyBelow() {
		return supportTwentyBelow;
	}
	public void setSupportTwentyBelow(String supportTwentyBelow) {
		this.supportTwentyBelow = supportTwentyBelow;
	}
	public String getSupportSixtyAbove() {
		return supportSixtyAbove;
	}
	public void setSupportSixtyAbove(String supportSixtyAbove) {
		this.supportSixtyAbove = supportSixtyAbove;
	}
	public String getManyChildDeduction() {
		return manyChildDeduction;
	}
	public void setManyChildDeduction(String manyChildDeduction) {
		this.manyChildDeduction = manyChildDeduction;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getSalTransBank() {
		return salTransBank;
	}
	public void setSalTransBank(String salTransBank) {
		this.salTransBank = salTransBank;
	}

}
