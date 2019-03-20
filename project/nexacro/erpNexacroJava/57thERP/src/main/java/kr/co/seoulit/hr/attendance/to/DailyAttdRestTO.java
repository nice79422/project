package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "ds_dailyAttdRest")
public class DailyAttdRestTO extends BaseTO{
	
	private String empCode;
	private String attdRestSeq;
	private String attdRestCode;
	private String requestDate;
	private String startDate;
	private String endDate;
	private String days;
	private String cost;
	private String cause;
	private String approvalStatus;
	private String empName;
	private String otherSudang;
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getAttdRestSeq() {
		return attdRestSeq;
	}
	public void setAttdRestSeq(String attdRestSeq) {
		this.attdRestSeq = attdRestSeq;
	}
	public String getAttdRestCode() {
		return attdRestCode;
	}
	public void setAttdRestCode(String attdRestCode) {
		this.attdRestCode = attdRestCode;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
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
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getOtherSudang() {
		return otherSudang;
	}
	public void setOtherSudang(String otherSudang) {
		this.otherSudang = otherSudang;
	}
	
	
}
