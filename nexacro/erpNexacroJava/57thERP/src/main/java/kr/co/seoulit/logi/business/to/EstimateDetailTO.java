package kr.co.seoulit.logi.business.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "ds_estimateDetail")
public class EstimateDetailTO extends BaseTO {

	String estimateDetailNo, estimateNo, itemCode, itemName, unitOfEstimate, dueDateOfEstimate,
		   estimateAmount, unitPriceOfEstimate, sumPriceOfEstimate, description;

	public String getEstimateDetailNo() {
		return estimateDetailNo;
	}

	public void setEstimateDetailNo(String estimateDetailNo) {
		this.estimateDetailNo = estimateDetailNo;
	}

	public String getEstimateNo() {
		return estimateNo;
	}

	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
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

	public String getUnitOfEstimate() {
		return unitOfEstimate;
	}

	public void setUnitOfEstimate(String unitOfEstimate) {
		this.unitOfEstimate = unitOfEstimate;
	}

	public String getDueDateOfEstimate() {
		return dueDateOfEstimate;
	}

	public void setDueDateOfEstimate(String dueDateOfEstimate) {
		this.dueDateOfEstimate = dueDateOfEstimate;
	}

	public String getEstimateAmount() {
		return estimateAmount;
	}

	public void setEstimateAmount(String estimateAmount) {
		this.estimateAmount = estimateAmount;
	}

	public String getUnitPriceOfEstimate() {
		return unitPriceOfEstimate;
	}

	public void setUnitPriceOfEstimate(String unitPriceOfEstimate) {
		this.unitPriceOfEstimate = unitPriceOfEstimate;
	}

	public String getSumPriceOfEstimate() {
		return sumPriceOfEstimate;
	}

	public void setSumPriceOfEstimate(String sumPriceOfEstimate) {
		this.sumPriceOfEstimate = sumPriceOfEstimate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}