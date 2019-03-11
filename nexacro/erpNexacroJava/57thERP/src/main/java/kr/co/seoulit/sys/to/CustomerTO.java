package kr.co.seoulit.sys.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_customer")
public class CustomerTO extends BaseTO{
	String customerCode,
	workplaceCode,
	customerName,
	customerType,
	customerCeo,
	businessLicenseNumber,
	socialSecurityNumber,
	customerBusinessConditions,
	customerBusinessItems,
	customerZipCode,
	customerBasicAddress,
	customerDetailAddress,
	customerTelNumber,
	customerFaxNumber;

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getWorkplaceCode() {
		return workplaceCode;
	}

	public void setWorkplaceCode(String workplaceCode) {
		this.workplaceCode = workplaceCode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerCeo() {
		return customerCeo;
	}

	public void setCustomerCeo(String customerCeo) {
		this.customerCeo = customerCeo;
	}

	public String getBusinessLicenseNumber() {
		return businessLicenseNumber;
	}

	public void setBusinessLicenseNumber(String businessLicenseNumber) {
		this.businessLicenseNumber = businessLicenseNumber;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public String getCustomerBusinessConditions() {
		return customerBusinessConditions;
	}

	public void setCustomerBusinessConditions(String customerBusinessConditions) {
		this.customerBusinessConditions = customerBusinessConditions;
	}

	public String getCustomerBusinessItems() {
		return customerBusinessItems;
	}

	public void setCustomerBusinessItems(String customerBusinessItems) {
		this.customerBusinessItems = customerBusinessItems;
	}

	public String getCustomerZipCode() {
		return customerZipCode;
	}

	public void setCustomerZipCode(String customerZipCode) {
		this.customerZipCode = customerZipCode;
	}

	public String getCustomerBasicAddress() {
		return customerBasicAddress;
	}

	public void setCustomerBasicAddress(String customerBasicAddress) {
		this.customerBasicAddress = customerBasicAddress;
	}

	public String getCustomerDetailAddress() {
		return customerDetailAddress;
	}

	public void setCustomerDetailAddress(String customerDetailAddress) {
		this.customerDetailAddress = customerDetailAddress;
	}

	public String getCustomerTelNumber() {
		return customerTelNumber;
	}

	public void setCustomerTelNumber(String customerTelNumber) {
		this.customerTelNumber = customerTelNumber;
	}

	public String getCustomerFaxNumber() {
		return customerFaxNumber;
	}

	public void setCustomerFaxNumber(String customerFaxNumber) {
		this.customerFaxNumber = customerFaxNumber;
	}

	
	
	
}
