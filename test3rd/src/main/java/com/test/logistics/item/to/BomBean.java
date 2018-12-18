package com.test.logistics.item.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BomBean extends ItemBean{
	private String level;
	private String bomCode;
	private String parentCode;
	private String nQuantity;
	private String netQuantity;
	private String necessaryQuantity;
	private String itemCode;
	private String lossRate;
	private String leadTime;
	
	
	
	
	
	
	public String getBomCode() {
		return bomCode;
	}
	public void setBomCode(String bomCode) {
		this.bomCode = bomCode;
	}
	public String getNecessaryQuantity() {
		return necessaryQuantity;
	}
	public void setNecessaryQuantity(String necessaryQuantity) {
		this.necessaryQuantity = necessaryQuantity;
	}
	public String getNetQuantity() {
		return netQuantity;
	}
	public void setNetQuantity(String netQuantity) {
		this.netQuantity = netQuantity;
	}
	public String getLeadTime() {
		return leadTime;
	}
	public void setLeadTime(String leadTime) {
		this.leadTime = leadTime;
	}
	public String getLossRate() {
		return lossRate;
	}
	public void setLossRate(String lossRate) {
		this.lossRate = lossRate;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getnQuantity() {
		return nQuantity;
	}
	public void setnQuantity(String nQuantity) {
		this.nQuantity = nQuantity;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
}
