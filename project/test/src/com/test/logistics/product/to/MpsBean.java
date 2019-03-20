package com.test.logistics.product.to;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.test.logistics.business.to.ContractDetailBean;
import com.test.logistics.item.to.ItemBean;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MpsBean {
	private ItemBean itemBean;
	private ContractDetailBean contractDetailBean;
	private String mpsCode;
	private String planQuantity;
	private String planDate;
	private String workplaceCode;
	private String contractDetailCode;
	private String mrpStatus;
	private String itemCode;
	private String status;

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
}
