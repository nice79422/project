package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_annualLeave")
public class AnnualLeaveTO extends BaseTO{

	private String empCode;
	private String standardYear;
	private String restDays;
	private String usableDays;
	private String usedDays;
	private String lengthOfService;
	
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getStandardYear() {
		return standardYear;
	}
	public void setStandardYear(String standardYear) {
		this.standardYear = standardYear;
	}
	public String getRestDays() {
		return restDays;
	}
	public void setRestDays(String restDays) {
		this.restDays = restDays;
	}
	public String getUsableDays() {
		return usableDays;
	}
	public void setUsableDays(String usableDays) {
		this.usableDays = usableDays;
	}
	public String getUsedDays() {
		return usedDays;
	}
	public void setUsedDays(String usedDays) {
		this.usedDays = usedDays;
	}
	public String getLengthOfService() {
		return lengthOfService;
	}
	public void setLengthOfService(String lengthOfService) {
		this.lengthOfService = lengthOfService;
	}
}
