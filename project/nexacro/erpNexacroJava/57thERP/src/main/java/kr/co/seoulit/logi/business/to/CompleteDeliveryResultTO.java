package kr.co.seoulit.logi.business.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "ds_completeDelivery")
public class CompleteDeliveryResultTO extends BaseTO {

	String contractNo, contractDetailNo, customerCode, itemName, itemCode, contractAmount, unitPriceOfContract, mpsNo,
			rItemCode, rItemName, itemClassification, mpsPlanDate, workInstructionNo, mrpGatheringNo, deliveryNo,
			deliveryDate, deliveryAmount, dItemCode;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractDetailNo() {
		return contractDetailNo;
	}

	public void setContractDetailNo(String contractDetailNo) {
		this.contractDetailNo = contractDetailNo;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(String contractAmount) {
		this.contractAmount = contractAmount;
	}

	public String getUnitPriceOfContract() {
		return unitPriceOfContract;
	}

	public void setUnitPriceOfContract(String unitPriceOfContract) {
		this.unitPriceOfContract = unitPriceOfContract;
	}

	public String getMpsNo() {
		return mpsNo;
	}

	public void setMpsNo(String mpsNo) {
		this.mpsNo = mpsNo;
	}

	public String getrItemCode() {
		return rItemCode;
	}

	public void setrItemCode(String rItemCode) {
		this.rItemCode = rItemCode;
	}

	public String getrItemName() {
		return rItemName;
	}

	public void setrItemName(String rItemName) {
		this.rItemName = rItemName;
	}

	public String getItemClassification() {
		return itemClassification;
	}

	public void setItemClassification(String itemClassification) {
		this.itemClassification = itemClassification;
	}

	public String getMpsPlanDate() {
		return mpsPlanDate;
	}

	public void setMpsPlanDate(String mpsPlanDate) {
		this.mpsPlanDate = mpsPlanDate;
	}

	public String getWorkInstructionNo() {
		return workInstructionNo;
	}

	public void setWorkInstructionNo(String workInstructionNo) {
		this.workInstructionNo = workInstructionNo;
	}

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
	}

	public String getDeliveryNo() {
		return deliveryNo;
	}

	public void setDeliveryNo(String deliveryNo) {
		this.deliveryNo = deliveryNo;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryAmount() {
		return deliveryAmount;
	}

	public void setDeliveryAmount(String deliveryAmount) {
		this.deliveryAmount = deliveryAmount;
	}

	public String getdItemCode() {
		return dItemCode;
	}

	public void setdItemCode(String dItemCode) {
		this.dItemCode = dItemCode;
	}

}