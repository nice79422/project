package kr.co.seoulit.logi.purchase.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_warehousing")
public class WarehousingTO extends BaseTO{
	String itemCode, orderNo, itemName, orderAmount,
	warehousingAmount, unitWarehousing, customer, description,
	warehousingDate,warehousingNo;

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getWarehousingAmount() {
		return warehousingAmount;
	}

	public void setWarehousingAmount(String warehousingAmount) {
		this.warehousingAmount = warehousingAmount;
	}

	public String getUnitWarehousing() {
		return unitWarehousing;
	}

	public void setUnitWarehousing(String unitWarehousing) {
		this.unitWarehousing = unitWarehousing;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWarehousingDate() {
		return warehousingDate;
	}

	public void setWarehousingDate(String warehousingDate) {
		this.warehousingDate = warehousingDate;
	}

	public String getWarehousingNo() {
		return warehousingNo;
	}

	public void setWarehousingNo(String warehousingNo) {
		this.warehousingNo = warehousingNo;
	}
}
