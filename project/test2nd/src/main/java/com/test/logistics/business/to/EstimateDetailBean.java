package com.test.logistics.business.to;

import com.test.logistics.item.to.ItemBean;

public class EstimateDetailBean {
	private ItemBean itemBean;
	private String estimateDetailCode;
	private String estimateAmount;
	private String itemCode;
	private String estimateDetailUnitPrice;
	private String estimateCode;
	private String clientCode;
	
	
	
	
	

	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}

	public String getEstimateDetailCode() {
		return estimateDetailCode;
	}
	public void setEstimateDetailCode(String estimateDetailCode) {
		this.estimateDetailCode = estimateDetailCode;
	}
	public String getEstimateAmount() {
		return estimateAmount;
	}
	public void setEstimateAmount(String estimateAmount) {
		this.estimateAmount = estimateAmount;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getEstimateDetailUnitPrice() {
		return estimateDetailUnitPrice;
	}
	public void setEstimateDetailUnitPrice(String estimateDetailUnitPrice) {
		this.estimateDetailUnitPrice = estimateDetailUnitPrice;
	}
	public String getEstimateCode() {
		return estimateCode;
	}
	public void setEstimateCode(String estimateCode) {
		this.estimateCode = estimateCode;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
}
