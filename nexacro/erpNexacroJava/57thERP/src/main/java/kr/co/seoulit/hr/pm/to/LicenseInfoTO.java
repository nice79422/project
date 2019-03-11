package kr.co.seoulit.hr.pm.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="dsLicenseInfo")
public class LicenseInfoTO extends BaseTO{

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
