package com.test.logistics.purchase.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchasingPlaceBean {
	
	private String purchasingPlaceCode;
	private String purchasingPlaceName;
	
	private String businessNumber;
	private String businessConditions;
	private String representName;
	private String tel;
	private String addr;
	private String postNumber;
	private String detailAddr;
	private String fax;

	
	
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
	
	public String getDetailAddr() {
		return detailAddr;
	}
	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}
	
	public String getPostNumber() {
		return postNumber;
	}
	public void setPostNumber(String postNumber) {
		this.postNumber = postNumber;
	}
	
	
	
	public String getPurchasingPlaceCode() {
		return purchasingPlaceCode;
	}
	public void setPurchasingPlaceCode(String purchasingPlaceCode) {
		this.purchasingPlaceCode = purchasingPlaceCode;
	}
	public String getPurchasingPlaceName() {
		return purchasingPlaceName;
	}
	public void setPurchasingPlaceName(String purchasingPlaceName) {
		this.purchasingPlaceName = purchasingPlaceName;
	}
	public String getBusinessNumber() {
		return businessNumber;
	}
	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}
	public String getBusinessConditions() {
		return businessConditions;
	}
	public void setBusinessConditions(String businessConditions) {
		this.businessConditions = businessConditions;
	}
	public String getRepresentName() {
		return representName;
	}
	public void setRepresentName(String representName) {
		this.representName = representName;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}

}
