package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_dailyAttd")
public class DailyAttdTO extends BaseTO {

	private String empCode;
	private String empName;
	private String dayAttdSeq;
	private String basicDay;
	private String attdTypeCode;
	private String time;
	private String approvalStatus;
	private String cost;
	private String cause;
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDayAttdSeq() {
		return dayAttdSeq;
	}
	public void setDayAttdSeq(String dayAttdSeq) {
		this.dayAttdSeq = dayAttdSeq;
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
	
}
