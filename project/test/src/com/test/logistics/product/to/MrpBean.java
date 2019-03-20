package com.test.logistics.product.to;

import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;

public class MrpBean {
	private String mrpCode;
	private String mpsCode;
	private String purchaseQuantity;
	private String itemCode;
	private String itemName;
	private String purchaseDate;
	private String requiredDate;
	private String clientCode;
	private String workspaceCode;
	private String contractDetailCode;
	private String level;
	private String purchaseStatus;
	private String totalPrice;
	private String lossRate;

	private String mrpGatheringCode;
	private String amount;

	private BomBean bomBean;
	private ItemBean itemBean;
	private MpsBean mpsBean;

	
	
	
	public String getLossRate() {
		return lossRate;
	}
	public void setLossRate(String lossRate) {
		this.lossRate = lossRate;
	}
	
	
	
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getPurchaseStatus() {
		return purchaseStatus;
	}
	public void setPurchaseStatus(String purchaseStatus) {
		this.purchaseStatus = purchaseStatus;
	}

	public String getContractDetailCode() {
		return contractDetailCode;
	}
	public void setContractDetailCode(String contractDetailCode) {
		this.contractDetailCode = contractDetailCode;
	}
	public String getWorkspaceCode() {
		return workspaceCode;
	}
	public void setWorkspaceCode(String workspaceCode) {
		this.workspaceCode = workspaceCode;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
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
	public BomBean getBomBean() {
		return bomBean;
	}
	public void setBomBean(BomBean bomBean) {
		this.bomBean = bomBean;
	}
	public MpsBean getMpsBean() {
		return mpsBean;
	}
	public void setMpsBean(MpsBean mpsBean) {
		this.mpsBean = mpsBean;
	}
	public String getMrpCode() {
		return mrpCode;
	}
	public void setMrpCode(String mrpCode) {
		this.mrpCode = mrpCode;
	}
	public String getMpsCode() {
		return mpsCode;
	}
	public void setMpsCode(String mpsCode) {
		this.mpsCode = mpsCode;
	}
	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getRequiredDate() {
		return requiredDate;
	}
	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}
	public String getMrpGatheringCode() {
		return mrpGatheringCode;
	}
	public void setMrpGatheringCode(String mrpGatheringCode) {
		this.mrpGatheringCode = mrpGatheringCode;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
