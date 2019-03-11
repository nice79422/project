package kr.co.seoulit.logi.purchase.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

/************************************************************************
@Package		kr.co.seoulit.logi.purchase.to
@Class			MaterialPaymentTO.java
@Author			kanghokyeong
@Description	재고 관련 
@Create			2019.02.21
@Last Update   	
************************************************************************/

@Dataset(name="ds_materialPayment")
public class MaterialPaymentTO extends BaseTO {

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getWorkInstructionNo() {
		return workInstructionNo;
	}

	public void setWorkInstructionNo(String workInstructionNo) {
		this.workInstructionNo = workInstructionNo;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(String paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getUnitPayment() {
		return unitPayment;
	}

	public void setUnitPayment(String unitPayment) {
		this.unitPayment = unitPayment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getInputItemNo() {
		return inputItemNo;
	}

	public void setInputItemNo(String inputItemNo) {
		this.inputItemNo = inputItemNo;
	}

	public String getReleaseNo() {
		return releaseNo;
	}

	public void setReleaseNo(String releaseNo) {
		this.releaseNo = releaseNo;
	}

	String itemCode, paymentDate, workInstructionNo, itemName,
	paymentAmount, unitPayment, description,inputItemNo,releaseNo;
	
}
