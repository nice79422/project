package com.test5th.hr.attendance.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsDailyAttd")
public class DailyAttdBean extends BaseBean{

	private String empCode;
	private String empName;
	private String dayAttdSeq;
	private String basicDay;
	private String attdTypeCode;
	private String time;
	private String approvalStatus;
	private String cost;
	private String cause;

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	public String getDayAttdSeq() {
		return dayAttdSeq;
	}
	public void setDayAttdSeq(String dayAttdSeq) {
		this.dayAttdSeq = dayAttdSeq;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getBasicDay() {
		return basicDay;
	}
	public void setBasicDay(String basicDay) {
		this.basicDay = basicDay;
	}
	public String getAttdTypeCode() {
		return attdTypeCode;
	}
	public void setAttdTypeCode(String attdTypeCode) {
		this.attdTypeCode = attdTypeCode;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Override
	public String toString() {
		return "DailyAttdBean [dayAttdSeq=" + dayAttdSeq + ", empCode=" + empCode + ", basicDay=" + basicDay
				+ ", attdTypeCode=" + attdTypeCode + ", time=" + time + ", approvalStatus=" + approvalStatus + "]";
	}
}
