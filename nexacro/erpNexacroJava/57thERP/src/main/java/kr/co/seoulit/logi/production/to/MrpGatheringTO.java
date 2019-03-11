package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_mrpGathering")
public class MrpGatheringTO extends BaseTO{

	String mrpGatheringNo, orderOrProductionStatus, itemCode, itemName,
	unitOfMrpGathering, necessaryAmount, dueDate, claimDate, onGoingProcessStatus;

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
	}

	public String getOrderOrProductionStatus() {
		return orderOrProductionStatus;
	}

	public void setOrderOrProductionStatus(String orderOrProductionStatus) {
		this.orderOrProductionStatus = orderOrProductionStatus;
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

	public String getUnitOfMrpGathering() {
		return unitOfMrpGathering;
	}

	public void setUnitOfMrpGathering(String unitOfMrpGathering) {
		this.unitOfMrpGathering = unitOfMrpGathering;
	}

	public String getNecessaryAmount() {
		return necessaryAmount;
	}

	public void setNecessaryAmount(String necessaryAmount) {
		this.necessaryAmount = necessaryAmount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getClaimDate() {
		return claimDate;
	}

	public void setClaimDate(String claimDate) {
		this.claimDate = claimDate;
	}

	public String getOnGoingProcessStatus() {
		return onGoingProcessStatus;
	}

	public void setOnGoingProcessStatus(String onGoingProcessStatus) {
		this.onGoingProcessStatus = onGoingProcessStatus;
	}
}


