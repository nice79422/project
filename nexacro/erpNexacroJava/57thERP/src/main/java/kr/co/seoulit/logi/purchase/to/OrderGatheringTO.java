package kr.co.seoulit.logi.purchase.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

/************************************************************************
@Package		kr.co.seoulit.logi.purchase.to
@Class			OrderGatheringTO.java
@Author			kanghokyeong
@Description	주문 관련
@Create			2019.02.22
@Last Update
************************************************************************/

@Dataset(name="ds_orderGathering")
public class OrderGatheringTO extends BaseTO {

	String itemCode, itemName, unitOfOrder, deliveryScheduleDate,
	amount, orderAmount, orderGatheringStatus, mrpGatheringNo;

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

	public String getUnitOfOrder() {
		return unitOfOrder;
	}

	public void setUnitOfOrder(String unitOfOrder) {
		this.unitOfOrder = unitOfOrder;
	}

	public String getDeliveryScheduleDate() {
		return deliveryScheduleDate;
	}

	public void setDeliveryScheduleDate(String deliveryScheduleDate) {
		this.deliveryScheduleDate = deliveryScheduleDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderGatheringStatus() {
		return orderGatheringStatus;
	}

	public void setOrderGatheringStatus(String orderGatheringStatus) {
		this.orderGatheringStatus = orderGatheringStatus;
	}

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
	}

}
