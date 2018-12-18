package com.test4th.hr.attendance.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsOverNight")
public class OverNightReportBean extends BaseBean{
	private String overNightSeq;
	private String basicDay;
	private String empCode;
	private String empName;
	private String approvalStatus;
	private String requestStatus;
	private String type;

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
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
}
