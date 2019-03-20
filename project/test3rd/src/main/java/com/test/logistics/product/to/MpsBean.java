package com.test.logistics.product.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.to.BomBean;
import com.test.logistics.item.to.ItemBean;
import com.test.logistics.item.to.StockBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MpsBean {
	private String mpsCode;
	private String contractDetailCode;
	private String planQuantity;
	private String planDate;
	private String workplaceCode;
	private String mrpStatus;
	private String itemCode;
	private String status;
	private String stockCode;
	private String mrpGatheringCode;
	
	private ItemBean itemBean;
	private BomBean bomBean;
	private StockBean stockBean;
	private ContractDetailBean contractDetailBean;
	private String purchasingPlaceCode;
	private String purchaseDate;
	private String purchaseQuantity;

	public String getPurchaseQuantity() {
		return purchaseQuantity;
	}
	public void setPurchaseQuantity(String purchaseQuantity) {
		this.purchaseQuantity = purchaseQuantity;
	}
	public String getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(String purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getPurchasingPlaceCode() {
		return purchasingPlaceCode;
	}
	public void setPurchasingPlaceCode(String purchasingPlaceCode) {
		this.purchasingPlaceCode = purchasingPlaceCode;
	}
	public String getMrpGatheringCode() {
		return mrpGatheringCode;
	}
	public void setMrpGatheringCode(String mrpGatheringCode) {
		this.mrpGatheringCode = mrpGatheringCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	public ContractDetailBean getContractDetailBean() {
		return contractDetailBean;
	}
	public void setContractDetailBean(ContractDetailBean contractDetailBean) {
		this.contractDetailBean = contractDetailBean;
	}
	public String getMpsCode() {
		return mpsCode;
	}
	public void setMpsCode(String mpsCode) {
		this.mpsCode = mpsCode;
	}
	public String getPlanQuantity() {
		return planQuantity;
	}
	public void setPlanQuantity(String planQuantity) {
		this.planQuantity = planQuantity;
	}
	public String getPlanDate() {
		return planDate;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public String getWorkplaceCode() {
		return workplaceCode;
	}
	public void setWorkplaceCode(String workplaceCode) {
		this.workplaceCode = workplaceCode;
	}
	public String getContractDetailCode() {
		return contractDetailCode;
	}
	public void setContractDetailCode(String contractDetailCode) {
		this.contractDetailCode = contractDetailCode;
	}
	public String getMrpStatus() {
		return mrpStatus;
	}
	public void setMrpStatus(String mrpStatus) {
		this.mrpStatus = mrpStatus;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public StockBean getStockBean() {
		return stockBean;
	}
	public void setStockBean(StockBean stockBean) {
		this.stockBean = stockBean;
	}
	public BomBean getBomBean() {
		return bomBean;
	}
	public void setBomBean(BomBean bomBean) {
		this.bomBean = bomBean;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	
}
