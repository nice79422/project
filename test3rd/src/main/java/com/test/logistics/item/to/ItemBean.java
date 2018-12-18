package com.test.logistics.item.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.logistics.business.to.ClientBean;
import com.test.logistics.business.to.ContractDetailBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemBean {
	private String itemCode;
	private String itemName;
	private String quantity;
	private String unit;
	private String unitPrice;
	private String productStatus;
	private String pStatus;
	private String purchasingPlaceCode;
	
	
	private StockBean stockBean;
	private BomBean bomBean;
	private ClientBean clientBean;
	private ContractDetailBean contractDetailBean;
	
	
	
	

	
	
	public String getPurchasingPlaceCode() {
		return purchasingPlaceCode;
	}
	public void setPurchasingPlaceCode(String purchasingPlaceCode) {
		this.purchasingPlaceCode = purchasingPlaceCode;
	}
	public ClientBean getClientBean() {
		return clientBean;
	}
	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}
	public ContractDetailBean getContractDetailBean() {
		return contractDetailBean;
	}
	public void setContractDetailBean(ContractDetailBean contractDetailBean) {
		this.contractDetailBean = contractDetailBean;
	}
	public BomBean getBomBean() {
		return bomBean;
	}
	public void setBomBean(BomBean bomBean) {
		this.bomBean = bomBean;
	}
	public StockBean getStockBean() {
		return stockBean;
	}
	public void setStockBean(StockBean stockBean) {
		this.stockBean = stockBean;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
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
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
	public String getpStatus() {
		return pStatus;
	}
	public void setpStatus(String pStatus) {
		this.pStatus = pStatus;
	}
}
