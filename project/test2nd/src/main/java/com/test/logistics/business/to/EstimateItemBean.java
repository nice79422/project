package com.test.logistics.business.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.base.to.BaseBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstimateItemBean extends BaseBean{

	String estimateItemCode;
	String estimateCode;
	String demandDate;
	String itemCode;
	String estimateAmount;
	String estimateDate;
	String clientCode;
	String itemName;
	String unit;
	String unitPrice;
	String contractStatus;
	String standByStatus;

	public String getStandByStatus() {
		return standByStatus;
	}
	public void setStandByStatus(String standByStatus) {
		this.standByStatus = standByStatus;
	}
	public String getContractStatus() {
		return contractStatus;
	}
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}
	
	public String getEstimateItemCode() {
		return estimateItemCode;
	}
	public void setEstimateItemCode(String estimateItemCode) {
		this.estimateItemCode = estimateItemCode;
	}
	
	public String getEstimateCode() {
		return estimateCode;
	}
	public void setEstimateCode(String estimateCode) {
		this.estimateCode = estimateCode;
	}
	public String getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(String demandDate) {
		this.demandDate = demandDate;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getEstimateAmount() {
		return estimateAmount;
	}
	public void setEstimateAmount(String estimateAmount) {
		this.estimateAmount = estimateAmount;
	}
	public String getEstimateDate() {
		return estimateDate;
	}
	public void setEstimateDate(String estimateDate) {
		this.estimateDate = estimateDate;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}



}
