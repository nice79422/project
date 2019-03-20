package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsCompany")
public class CompanyBean extends BaseBean {

	private String companyCode;
	private String companyName;
	private String companyDivision;
	private String businessLicenseNumber;
	private String corporationLicenseNumber;
	private String companyCeoName;
	private String companyBusinessConditions;
	private String companyBusinessItems;
	private String companyZipCode;
	private String companyBasicAddress;
	private String companyDetailAddress;
	private String companyTelNumber;
	private String companyFaxNumber;
	private String companyEstablishmentDate;
	private String companyOpenDate;

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyDivision() {
		return companyDivision;
	}

	public void setCompanyDivision(String companyDivision) {
		this.companyDivision = companyDivision;
	}

	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}

	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}

	public String getCorporationLicenseNumber() {
		return corporationLicenseNumber;
	}

	public void setCorporationLicenseNumber(String corporationLicenseNumber) {
		this.corporationLicenseNumber = corporationLicenseNumber;
	}

	public String getCompanyCeoName() {
		return companyCeoName;
	}

	public void setCompanyCeoName(String companyCeoName) {
		this.companyCeoName = companyCeoName;
	}

	public String getCompanyBusinessConditions() {
		return companyBusinessConditions;
	}

	public void setCompanyBusinessConditions(String companyBusinessConditions) {
		this.companyBusinessConditions = companyBusinessConditions;
	}

	public String getCompanyBusinessItems() {
		return companyBusinessItems;
	}

	public void setCompanyBusinessItems(String companyBusinessItems) {
		this.companyBusinessItems = companyBusinessItems;
	}

	public String getCompanyZipCode() {
		return companyZipCode;
	}

	public void setCompanyZipCode(String companyZipCode) {
		this.companyZipCode = companyZipCode;
	}

	public String getCompanyBasicAddress() {
		return companyBasicAddress;
	}

	public void setCompanyBasicAddress(String companyBasicAddress) {
		this.companyBasicAddress = companyBasicAddress;
	}

	public String getCompanyDetailAddress() {
		return companyDetailAddress;
	}

	public void setCompanyDetailAddress(String companyDetailAddress) {
		this.companyDetailAddress = companyDetailAddress;
	}

	public String getCompanyTelNumber() {
		return companyTelNumber;
	}

	public void setCompanyTelNumber(String companyTelNumber) {
		this.companyTelNumber = companyTelNumber;
	}

	public String getCompanyFaxNumber() {
		return companyFaxNumber;
	}

	public void setCompanyFaxNumber(String companyFaxNumber) {
		this.companyFaxNumber = companyFaxNumber;
	}

	public String getCompanyEstablishmentDate() {
		return companyEstablishmentDate;
	}

	public void setCompanyEstablishmentDate(String companyEstablishmentDate) {
		this.companyEstablishmentDate = companyEstablishmentDate;
	}

	public String getCompanyOpenDate() {
		return companyOpenDate;
	}

	public void setCompanyOpenDate(String companyOpenDate) {
		this.companyOpenDate = companyOpenDate;
	}

}