package com.test.logistics.purchase.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PurchaseBean {
	private String purchaseCode;
	private String clientCode;
	private String itemCode;
	private String purchaseFixDate;
	private String purchaseAmount;
	private String 	mrpFixCode;
	private String 	mpsCode;
	private String status;
	
	
	private String mrpGatheringCode;
	private String purchasingPlaceCode;
	private String purchaseDate;
	
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPurchaseFixDate() {
		return purchaseFixDate;
	}
	public void setPurchaseFixDate(String purchaseFixDate) {
		this.purchaseFixDate = purchaseFixDate;
	}
	public String getMpsCode() {
		return mpsCode;
	}
	public void setMpsCode(String mpsCode) {
		this.mpsCode = mpsCode;
	}
	private String 	purchasePrice;

	public String getPurchaseCode() {
		return purchaseCode;
	}
	public void setPurchaseCode(String purchaseCode) {
		this.purchaseCode = purchaseCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getPurchaseAmount() {
		return purchaseAmount;
	}
	public void setPurchaseAmount(String purchaseAmount) {
		this.purchaseAmount = purchaseAmount;
	}
	public String getMrpFixCode() {
		return mrpFixCode;
	}
	public void setMrpFixCode(String mrpFixCode) {
		this.mrpFixCode = mrpFixCode;
	}
	public String getPurchasePrice() {
		return purchasePrice;
	}
	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public String getMrpGatheringCode() {
		return mrpGatheringCode;
	}
	public void setMrpGatheringCode(String mrpGatheringCode) {
		this.mrpGatheringCode = mrpGatheringCode;
	}
	public String getPurchasingPlaceCode() {
		return purchasingPlaceCode;
	}
	public void setPurchasingPlaceCode(String purchasingPlaceCode) {
		this.purchasingPlaceCode = purchasingPlaceCode;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
}
