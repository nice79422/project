package com.test.logistics.item.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StockBean {
	
	
	private ItemBean itemBean;
	private String stockCode;
	private String warehouseCode;
	private String stockAmount;
	private String outputAmount;
	private String inputAmount;
	private String outputDate;
	private String inputDate;
	private String itemCode;
	
	private String inputExpecAmount;
	private String inputExpecDate;
	private String outputExpecAmount;
	private String outputExpecDate;
	private String produceStatus;
	private String parentCode;
	
	public String getInputExpecAmount() {
		return inputExpecAmount;
	}
	public void setInputExpecAmount(String inputExpecAmount) {
		this.inputExpecAmount = inputExpecAmount;
	}
	public String getInputExpecDate() {
		return inputExpecDate;
	}
	public void setInputExpecDate(String inputExpecDate) {
		this.inputExpecDate = inputExpecDate;
	}
	public String getOutputExpecAmount() {
		return outputExpecAmount;
	}
	public void setOutputExpecAmount(String outputExpecAmount) {
		this.outputExpecAmount = outputExpecAmount;
	}
	public String getOutputExpecDate() {
		return outputExpecDate;
	}
	public void setOutputExpecDate(String outputExpecDate) {
		this.outputExpecDate = outputExpecDate;
	}
	public String getProduceStatus() {
		return produceStatus;
	}
	public void setProduceStatus(String produceStatus) {
		this.produceStatus = produceStatus;
	}

	
	
	
	
	
	
	
	
	
	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	public String getStockCode() {
		return stockCode;
	}
	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}
	public String getWarehouseCode() {
		return warehouseCode;
	}
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	public String getStockAmount() {
		return stockAmount;
	}
	public void setStockAmount(String stockAmount) {
		this.stockAmount = stockAmount;
	}
	public String getOutputAmount() {
		return outputAmount;
	}
	public void setOutputAmount(String outputAmount) {
		this.outputAmount = outputAmount;
	}
	public String getInputAmount() {
		return inputAmount;
	}
	public void setInputAmount(String inputAmount) {
		this.inputAmount = inputAmount;
	}
	
	public String getOutputDate() {
		return outputDate;
	}
	public void setOutputDate(String outputDate) {
		this.outputDate = outputDate;
	}
	public String getInputDate() {
		return inputDate;
	}
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}
	
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
