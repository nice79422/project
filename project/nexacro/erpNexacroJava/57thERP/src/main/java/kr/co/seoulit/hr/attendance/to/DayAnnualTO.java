package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_dayAnnual")
public class DayAnnualTO extends BaseTO{

	private String dayAnnualSeq;
	private String empCode;
	private String startDate;
	private String endDate;
	private String causeCode;
	private String detailCause;
	private String standardYear;
	private String annualCode;
	private String days;
	private String empName;
	private String approvalStatus;
	
	public String getDayAnnualSeq() {
		return dayAnnualSeq;
	}
	public void setDayAnnualSeq(String dayAnnualSeq) {
		this.dayAnnualSeq = dayAnnualSeq;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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
	public String getCauseCode() {
		return causeCode;
	}
	public void setCauseCode(String causeCode) {
		this.causeCode = causeCode;
	}
	public String getDetailCause() {
		return detailCause;
	}
	public void setDetailCause(String detailCause) {
		this.detailCause = detailCause;
	}
	public String getStandardYear() {
		return standardYear;
	}
	public void setStandardYear(String standardYear) {
		this.standardYear = standardYear;
	}
	public String getAnnualCode() {
		return annualCode;
	}
	public void setAnnualCode(String annualCode) {
		this.annualCode = annualCode;
	}
	public String getDays() {
		return days;
	}
	public void setDays(String days) {
		this.days = days;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	
	
}

