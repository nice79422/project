package kr.co.seoulit.hr.emp.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="gds_employee")
public class EmployeeTO extends BaseTO{

	String 	empCode,
	empName,
	password,
	positionCode,
	authorityCode,
	deptCode,
	businessPlaceCode;

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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}

	public String getAuthorityCode() {
		return authorityCode;
	}

	public void setAuthorityCode(String authorityCode) {
		this.authorityCode = authorityCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getBusinessPlaceCode() {
		return businessPlaceCode;
	}

	public void setBusinessPlaceCode(String businessPlaceCode) {
		this.businessPlaceCode = businessPlaceCode;
	}


}
