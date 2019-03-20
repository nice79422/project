package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_mrp")
public class MrpTO extends BaseTO{
	String mrpNo, mpsNo, mrpGatheringNo, itemClassifcation, itemCode,
	itemName, unitOfMrp, requiredAmount, orderDate, requiredDate, mrpGatheringStatus;

	public String getMrpNo() {
		return mrpNo;
	}

	public void setMrpNo(String mrpNo) {
		this.mrpNo = mrpNo;
	}

	public String getMpsNo() {
		return mpsNo;
	}

	public void setMpsNo(String mpsNo) {
		this.mpsNo = mpsNo;
	}

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
	}

	public String getItemClassifcation() {
		return itemClassifcation;
	}

	public void setItemClassifcation(String itemClassifcation) {
		this.itemClassifcation = itemClassifcation;
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

	public String getRequiredAmount() {
		return requiredAmount;
	}

	public void setRequiredAmount(String requiredAmount) {
		this.requiredAmount = requiredAmount;
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

	public String getMrpGatheringStatus() {
		return mrpGatheringStatus;
	}

	public void setMrpGatheringStatus(String mrpGatheringStatus) {
		this.mrpGatheringStatus = mrpGatheringStatus;
	}
}
