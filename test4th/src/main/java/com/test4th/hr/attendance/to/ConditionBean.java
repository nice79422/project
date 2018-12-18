package com.test4th.hr.attendance.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsCondition")
public class ConditionBean extends BaseBean{

	private String deptCode;
	private String approvalStatus;
	private String basicDay;

	public String getBasicDay() {
		return basicDay;
	}
	public void setBasicDay(String basicDay) {
		this.basicDay = basicDay;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	@Override
	public String toString() {
		return "ConditionBean [deptCode=" + deptCode + ", approvalStatus=" + approvalStatus + ", basicDay=" + basicDay
				+ "]";
	}
}
