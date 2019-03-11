package kr.co.seoulit.logi.purchase.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_orderDetail")
public class OrderDetailTO extends BaseTO {

	String orderDetailNo, orderNo, itemCode, itemName,
	unitOfOrder, dueDateOfOrder, deliveryScheduleDate,
	orderAmount, unitPriceOfOrder, sumPriceOfOrder, description,
	mrpGatheringNo, orderDetailStatus,slipRegistStatus;

	public String getSlipRegistStatus() {
		return slipRegistStatus;
	}

	public void setSlipRegistStatus(String slipRegistStatus) {
		this.slipRegistStatus = slipRegistStatus;
	};

	public String getOrderDetailNo() {
		return orderDetailNo;
	}

	public void setOrderDetailNo(String orderDetailNo) {
		this.orderDetailNo = orderDetailNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
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

	public String getUnitOfOrder() {
		return unitOfOrder;
	}

	public void setUnitOfOrder(String unitOfOrder) {
		this.unitOfOrder = unitOfOrder;
	}

	public String getDueDateOfOrder() {
		return dueDateOfOrder;
	}

	public void setDueDateOfOrder(String dueDateOfOrder) {
		this.dueDateOfOrder = dueDateOfOrder;
	}

	public String getDeliveryScheduleDate() {
		return deliveryScheduleDate;
	}

	public void setDeliveryScheduleDate(String deliveryScheduleDate) {
		this.deliveryScheduleDate = deliveryScheduleDate;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getUnitPriceOfOrder() {
		return unitPriceOfOrder;
	}

	public void setUnitPriceOfOrder(String unitPriceOfOrder) {
		this.unitPriceOfOrder = unitPriceOfOrder;
	}

	public String getSumPriceOfOrder() {
		return sumPriceOfOrder;
	}

	public void setSumPriceOfOrder(String sumPriceOfOrder) {
		this.sumPriceOfOrder = sumPriceOfOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
	}

	public String getOrderDetailStatus() {
		return orderDetailStatus;
	}

	public void setOrderDetailStatus(String orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

}
