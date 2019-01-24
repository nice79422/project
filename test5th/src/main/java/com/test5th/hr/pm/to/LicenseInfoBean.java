package com.test5th.hr.pm.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsLicenseInfo")
public class LicenseInfoBean extends BaseBean{

	private String empCode;
	private String licenseSeq;
	private String licenseName;
	private String startDate;
	private String endDate;
	private String licenseCenter;
	private String licenseLevel;
	private String licenseNo;

	public String getLicenseSeq() {
		return licenseSeq;
	}
	public void setLicenseSeq(String licenseSeq) {
		this.licenseSeq = licenseSeq;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getLicenseName() {
		return licenseName;
	}
	public void setLicenseName(String licenseName) {
		this.licenseName = licenseName;
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
	public String getLicenseCenter() {
		return licenseCenter;
	}
	public void setLicenseCenter(String licenseCenter) {
		this.licenseCenter = licenseCenter;
	}
	public String getLicenseLevel() {
		return licenseLevel;
	}
	public void setLicenseLevel(String licenseLevel) {
		this.licenseLevel = licenseLevel;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
}
