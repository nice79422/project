package kr.co.seoulit.hr.appointment.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_personalAppointment")
public class PersonalAppointmentTO extends BaseTO{

	String 	appointmentNo,
	employeeNo,
	employeeName,
	department,
	position,
	applyDate,
	note,
	appointmentDivision,
	applyStatus;

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAppointmentDivision() {
		return appointmentDivision;
	}

	public void setAppointmentDivision(String appointmentDivision) {
		this.appointmentDivision = appointmentDivision;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	
	
}
