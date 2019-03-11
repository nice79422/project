package kr.co.seoulit.hr.appointment.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_appointment")
public class AppointmentTO extends BaseTO{

	String 	appointmentNo,
	appointmentTitle,
	appointmentDivision,
	appointmentDate,
	appointmentDirector,
	approvalStatus,
	note,
	appointmentSeq;

	public String getAppointmentNo() {
		return appointmentNo;
	}

	public void setAppointmentNo(String appointmentNo) {
		this.appointmentNo = appointmentNo;
	}

	public String getAppointmentTitle() {
		return appointmentTitle;
	}

	public void setAppointmentTitle(String appointmentTitle) {
		this.appointmentTitle = appointmentTitle;
	}

	public String getAppointmentDivision() {
		return appointmentDivision;
	}

	public void setAppointmentDivision(String appointmentDivision) {
		this.appointmentDivision = appointmentDivision;
	}

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentDirector() {
		return appointmentDirector;
	}

	public void setAppointmentDirector(String appointmentDirector) {
		this.appointmentDirector = appointmentDirector;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAppointmentSeq() {
		return appointmentSeq;
	}

	public void setAppointmentSeq(String appointmentSeq) {
		this.appointmentSeq = appointmentSeq;
	}
	
}
