package kr.co.seoulit.hr.appointment.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_personalAppointmentDetail")
public class PersonalAppointmentDetailTO extends BaseTO{

	String 	appointmentNo,
	employeeNo,
	appointmentHistory,
	currentInformation,
	preAppointmentInformation,
	appointmentInformation,
	note;
	
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

	public String getAppointmentHistory() {
		return appointmentHistory;
	}

	public void setAppointmentHistory(String appointmentHistory) {
		this.appointmentHistory = appointmentHistory;
	}

	public String getCurrentInformation() {
		return currentInformation;
	}

	public void setCurrentInformation(String currentInformation) {
		this.currentInformation = currentInformation;
	}

	public String getPreAppointmentInformation() {
		return preAppointmentInformation;
	}

	public void setPreAppointmentInformation(String preAppointmentInformation) {
		this.preAppointmentInformation = preAppointmentInformation;
	}

	public String getAppointmentInformation() {
		return appointmentInformation;
	}

	public void setAppointmentInformation(String appointmentInformation) {
		this.appointmentInformation = appointmentInformation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
