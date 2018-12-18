package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsDepartment")
public class DepartmentBean extends BaseBean{

	private String deptCode;
	private String deptName;
	private String deptTel;

	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDeptTel() {
		return deptTel;
	}
	public void setDeptTEl(String deptTel) {
		this.deptTel = deptTel;
	}

	@Override
	public String toString() {
		return "DepartmentBean [deptCode=" + deptCode + ", deptName=" + deptName + ", deptTel=" + deptTel + "]";
	}
}
