package kr.co.seoulit.logi.production.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_workInstruction")
public class WorkInstructionTO extends BaseTO{

	String workInstructionNo, mrpGatheringNo, itemCode,
	itemName, instructionDate, unitOfWorkInstruction,
	workInstructionAmount, productionStatus, description,
	workInstructionStatus;

	public String getWorkInstructionNo() {
		return workInstructionNo;
	}

	public void setWorkInstructionNo(String workInstructionNo) {
		this.workInstructionNo = workInstructionNo;
	}

	public String getMrpGatheringNo() {
		return mrpGatheringNo;
	}

	public void setMrpGatheringNo(String mrpGatheringNo) {
		this.mrpGatheringNo = mrpGatheringNo;
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

	public String getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
	}

	public String getUnitOfWorkInstruction() {
		return unitOfWorkInstruction;
	}

	public void setUnitOfWorkInstruction(String unitOfWorkInstruction) {
		this.unitOfWorkInstruction = unitOfWorkInstruction;
	}

	public String getWorkInstructionAmount() {
		return workInstructionAmount;
	}

	public void setWorkInstructionAmount(String workInstructionAmount) {
		this.workInstructionAmount = workInstructionAmount;
	}

	public String getProductionStatus() {
		return productionStatus;
	}

	public void setProductionStatus(String productionStatus) {
		this.productionStatus = productionStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWorkInstructionStatus() {
		return workInstructionStatus;
	}

	public void setWorkInstructionStatus(String workInstructionStatus) {
		this.workInstructionStatus = workInstructionStatus;
	}










}