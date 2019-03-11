package kr.co.seoulit.sys.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;


@Dataset(name="ds_company")
public class CompanyTO extends BaseTO{
	String companyCode
	,companyName
	,companyDivision
	,businessLicenseNumber
	,corporationLicenseNumber
	,companyCeoName
	,companyBusinessConditions
	,companyBusinessItems
	,companyZipCode
	,companyBasicAddress
	,companyDetailAddress
	,companyTelNumber
	,companyFaxNumber
	,companyEstablishmentDate
	,companyOpenDate;

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
