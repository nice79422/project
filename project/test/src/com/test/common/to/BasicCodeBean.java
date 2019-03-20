package com.test.common.to;

import com.test.logistics.item.to.ItemBean;

public class BasicCodeBean {
	private ItemBean itemBean;
	private String basicCode;
	private String basicCodeName;
	private String detailCode;
	private String detailCodeName;
	private String itemName;

	
	public ItemBean getItemBean() {
		return itemBean;
	}
	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	
	public String getBasicCode() {
		return basicCode;
	}
	public void setBasicCode(String basicCode) {
		this.basicCode = basicCode;
	}
	public String getitemCode() {
		return itemName;
	}
	public void setitemName(String itemName) {
		this.itemName = itemName;
	}
	public String getBasicCodeName() {
		return basicCodeName;
	}
	public void setBasicCodeName(String basicCodeName) {
		this.basicCodeName = basicCodeName;
	}
	public String getDetailCode() {
		return detailCode;
	}
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
	public String getDetailCodeName() {
		return detailCodeName;
	}
	public void setDetailCodeName(String detailCodeName) {
		this.detailCodeName = detailCodeName;
	}
}
