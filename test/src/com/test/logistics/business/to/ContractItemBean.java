package com.test.logistics.business.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.common.to.BaseBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractItemBean extends BaseBean{

	String contractItemCode;
	String contractCode;
	String demandDate;
	String contractAmount;
	String mpsStatus;
	String itemCode;
	String contractDate;
	String accountCode;
	String itemName;
	String unit;
	String shippingStatus;
	String stockAmount;
	String deliveryscheduledate;
	String estimateCode;
	String produceStatus;
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
	public String getProduceStatus() {
		return produceStatus;
	}
	public void setProduceStatus(String produceStatus) {
		this.produceStatus = produceStatus;
	}
	public String getEstimateCode() {
		return estimateCode;
	}
	public void setEstimateCode(String estimateCode) {
		this.estimateCode = estimateCode;
	}
	public String getDeliveryScheduleDate() {
		return contractItemCode;
	}
	public void setDeliveryScheduleDate(String deliveryscheduledate) {
		this.deliveryscheduledate = deliveryscheduledate;
	}
	public String getContractItemCode() {
		return contractItemCode;
	}
	public void setContractItemCode(String contractItemCode) {
		this.contractItemCode = contractItemCode;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getDemandDate() {
		return demandDate;
	}
	public void setDemandDate(String demandDate) {
		this.demandDate = demandDate;
	}
	public String getContractAmount() {
		return contractAmount;
	}
	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}
	public String getMpsStatus() {
		return mpsStatus;
	}
	public void setMpsStatus(String mpsStatus) {
		this.mpsStatus = mpsStatus;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
	}
	public String getAccountCode() {
		return accountCode;
	}
	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
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
	public String getShippingStatus() {
		return shippingStatus;
	}
	public void setShippingStatus(String shippingStatus) {
		this.shippingStatus = shippingStatus;
	}
	public String getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}


}
