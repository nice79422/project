package kr.co.seoulit.logi.business.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "ds_contractDetail")
public class ContractDetailTO extends BaseTO {

	String contractDetailNo, contractNo, itemCode, itemName, unitOfContract, dueDateOfContract, contractAmount,
			unitPriceOfContract, sumPriceOfContract, mpsApplyStatus, deliveryStatus
			, description, deliveryRemain,slipRegistStatus;


	public String getSlipRegistStatus() {
		return slipRegistStatus;
	}

	public void setSlipRegistStatus(String slipRegistStatus) {
		this.slipRegistStatus = slipRegistStatus;
	}

	public String getContractDetailNo() {
		return contractDetailNo;
	}

	public void setContractDetailNo(String contractDetailNo) {
		this.contractDetailNo = contractDetailNo;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
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

	public String getUnitOfContract() {
		return unitOfContract;
	}

	public void setUnitOfContract(String unitOfContract) {
		this.unitOfContract = unitOfContract;
	}

	public String getDueDateOfContract() {
		return dueDateOfContract;
	}

	public void setDueDateOfContract(String dueDateOfContract) {
		this.dueDateOfContract = dueDateOfContract;
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

	public String getSumPriceOfContract() {
		return sumPriceOfContract;
	}

	public void setSumPriceOfContract(String sumPriceOfContract) {
		this.sumPriceOfContract = sumPriceOfContract;
	}

	public String getMpsApplyStatus() {
		return mpsApplyStatus;
	}

	public void setMpsApplyStatus(String mpsApplyStatus) {
		this.mpsApplyStatus = mpsApplyStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDeliveryRemain() {
		return deliveryRemain;
	}

	public void setDeliveryRemain(String deliveryRemain) {
		this.deliveryRemain = deliveryRemain;
	}

}