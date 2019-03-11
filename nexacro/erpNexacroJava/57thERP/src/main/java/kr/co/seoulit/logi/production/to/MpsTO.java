package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_mps")
public class MpsTO  extends BaseTO{

	String mpsNo, mpsPlanClassification, contractDetailNo,
	salesPlanNo, itemCode, itemName, unitOfMps,	mpsPlanDate,
	mpsPlanAmount, dueDateOfMps, scheduledEndDate, mrpApplyStatus, description;

	
	
	public String getMpsNo() {
		return mpsNo;
	}

	public void setMpsNo(String mpsNo) {
		this.mpsNo = mpsNo;
	}

	public String getMpsPlanClassification() {
		return mpsPlanClassification;
	}

	public void setMpsPlanClassification(String mpsPlanClassification) {
		this.mpsPlanClassification = mpsPlanClassification;
	}

	public String getContractDetailNo() {
		return contractDetailNo;
	}

	public void setContractDetailNo(String contractDetailNo) {
		this.contractDetailNo = contractDetailNo;
	}

	public String getSalesPlanNo() {
		return salesPlanNo;
	}

	public void setSalesPlanNo(String salesPlanNo) {
		this.salesPlanNo = salesPlanNo;
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

	public String getUnitOfMps() {
		return unitOfMps;
	}

	public void setUnitOfMps(String unitOfMps) {
		this.unitOfMps = unitOfMps;
	}

	public String getMpsPlanDate() {
		return mpsPlanDate;
	}

	public void setMpsPlanDate(String mpsPlanDate) {
		this.mpsPlanDate = mpsPlanDate;
	}

	public String getMpsPlanAmount() {
		return mpsPlanAmount;
	}

	public void setMpsPlanAmount(String mpsPlanAmount) {
		this.mpsPlanAmount = mpsPlanAmount;
	}

	public String getDueDateOfMps() {
		return dueDateOfMps;
	}

	public void setDueDateOfMps(String dueDateOfMps) {
		this.dueDateOfMps = dueDateOfMps;
	}

	public String getScheduledEndDate() {
		return scheduledEndDate;
	}

	public void setScheduledEndDate(String scheduledEndDate) {
		this.scheduledEndDate = scheduledEndDate;
	}

	public String getMrpApplyStatus() {
		return mrpApplyStatus;
	}

	public void setMrpApplyStatus(String mrpApplyStatus) {
		this.mrpApplyStatus = mrpApplyStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
