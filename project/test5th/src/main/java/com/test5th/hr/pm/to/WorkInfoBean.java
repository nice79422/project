package com.test5th.hr.pm.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsWorkInfo")
public class WorkInfoBean extends BaseBean {

	private String hireDate;
	private String retireDate;
	private String employeementTypeCode;
	private String retireCause;
	private String probationStatus;
	private String probationExpireDate;
	private String projectCode;
	private String jikjongCode;
	private String empCode;
	private String empName;

	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getRetireDate() {
		return retireDate;
	}
	public void setRetireDate(String retireDate) {
		this.retireDate = retireDate;
	}
	public String getEmployeementTypeCode() {
		return employeementTypeCode;
	}
	public void setEmployeementTypeCode(String employeementTypeCode) {
		this.employeementTypeCode = employeementTypeCode;
	}
	public String getRetireCause() {
		return retireCause;
	}
	public void setRetireCause(String retireCause) {
		this.retireCause = retireCause;
	}
	public String getProbationStatus() {
		return probationStatus;
	}
	public void setProbationStatus(String probationStatus) {
		this.probationStatus = probationStatus;
	}
	public String getProbationExpireDate() {
		return probationExpireDate;
	}
	public void setProbationExpireDate(String probationExpireDate) {
		this.probationExpireDate = probationExpireDate;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getJikjongCode() {
		return jikjongCode;
	}
	public void setJikjongCode(String jikjongCode) {
		this.jikjongCode = jikjongCode;
	}
}
