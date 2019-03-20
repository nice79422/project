package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_mrpOpenTemp")
public class MrpOpenTempTO extends BaseTO{

	String mpsNo, bomNo, itemClassification, itemCode, itemName,
	unitOfMrp, planAmount, orderDate, requiredDate, totalLossRate,
	caculatedAmount, requiredAmount;

	public String getMpsNo() {
		return mpsNo;
	}

	public void setMpsNo(String mpsNo) {
		this.mpsNo = mpsNo;
	}

	public String getBomNo() {
		return bomNo;
	}

	public void setBomNo(String bomNo) {
		this.bomNo = bomNo;
	}

	public String getItemClassification() {
		return itemClassification;
	}

	public void setItemClassification(String itemClassification) {
		this.itemClassification = itemClassification;
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

	public String getUnitOfMrp() {
		return unitOfMrp;
	}

	public void setUnitOfMrp(String unitOfMrp) {
		this.unitOfMrp = unitOfMrp;
	}

	public String getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(String planAmount) {
		this.planAmount = planAmount;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public String getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(String requiredDate) {
		this.requiredDate = requiredDate;
	}

	public String getTotalLossRate() {
		return totalLossRate;
	}

	public void setTotalLossRate(String totalLossRate) {
		this.totalLossRate = totalLossRate;
	}

	public String getCaculatedAmount() {
		return caculatedAmount;
	}

	public void setCaculatedAmount(String caculatedAmount) {
		this.caculatedAmount = caculatedAmount;
	}

	public String getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(String requiredAmount) {
		this.requiredAmount = requiredAmount;
	}





}
