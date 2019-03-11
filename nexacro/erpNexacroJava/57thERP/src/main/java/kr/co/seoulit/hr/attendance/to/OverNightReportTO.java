package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_overNight")
public class OverNightReportTO extends BaseTO {
	private String overNightSeq;
	private String basicDay;
	private String empCode;
	private String empName;
	private String approvalStatus;
	private String requestStatus;
	private String type;
	private String requestDate;
	private String fromTime;
	private String toTime;
	
	public String getOverNightSeq() {
		return overNightSeq;
	}
	public void setOverNightSeq(String overNightSeq) {
		this.overNightSeq = overNightSeq;
	}
	public String getBasicDay() {
		return basicDay;
	}
	public void setBasicDay(String basicDay) {
		this.basicDay = basicDay;
	}
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
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getFromTime() {
		return fromTime;
	}
	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}
	public String getToTime() {
		return toTime;
	}
	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	
	
}
