package com.test.logistics.business.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.logistics.item.to.ItemBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractDetailBean {
	private ItemBean itemBean;
	private String contractDetailCode;
	private String supplyDate;
	private String supplyAmount;
	private String contractCode;
	private String itemCode;
	private String mpsStatus;
	private String outputStatus;
	private String itemName;
	
	private String clientCode;
	private String contractQuantity;
	private String deliveryScheduleDate;
	
	public String getDeliveryScheduleDate() {
		return deliveryScheduleDate;
	}
	public void setDeliveryScheduleDate(String deliveryScheduleDate) {
		this.deliveryScheduleDate = deliveryScheduleDate;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	public String getContractQuantity() {
		return contractQuantity;
	}
	public void setContractQuantity(String contractQuantity) {
		this.contractQuantity = contractQuantity;
	}
	
	
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	
	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	public String getContractDetailCode() {
		return contractDetailCode;
	}
	public void setContractDetailCode(String contractDetailCode) {
		this.contractDetailCode = contractDetailCode;
	}
	public String getSupplyDate() {
		return supplyDate;
	}
	public void setSupplyDate(String supplyDate) {
		this.supplyDate = supplyDate;
	}
	public String getSupplyAmount() {
		return supplyAmount;
	}
	public void setSupplyAmount(String supplyAmount) {
		this.supplyAmount = supplyAmount;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getMpsStatus() {
		return mpsStatus;
	}
	public void setMpsStatus(String mpsStatus) {
		this.mpsStatus = mpsStatus;
	}
	public String getOutputStatus() {
		return outputStatus;
	}
	public void setOutputStatus(String outputStatus) {
		this.outputStatus = outputStatus;
	}

}
