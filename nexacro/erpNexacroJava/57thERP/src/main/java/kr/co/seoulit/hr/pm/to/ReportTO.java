package kr.co.seoulit.hr.pm.to;


import kr.co.seoulit.common.to.BaseTO;


public class ReportTO extends BaseTO{

	private String empCode;
	private String empName;
	private String positionName;
	private String deptName;
	private String detailCodeName;
	private String baseAddress;
	private String detailAddress;
	private String hireDate;
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
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getDetailCodeName() {
		return detailCodeName;
	}
	public void setDetailCodeName(String detailCodeName) {
		this.detailCodeName = detailCodeName;
	}
	public String getBaseAddress() {
		return baseAddress;
	}
	public void setBaseAddress(String baseAddress) {
		this.baseAddress = baseAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}




}
