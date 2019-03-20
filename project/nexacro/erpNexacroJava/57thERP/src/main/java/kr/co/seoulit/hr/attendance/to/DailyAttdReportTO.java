package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "ds_dailyAttdReport")
public class DailyAttdReportTO extends BaseTO {
	
	private String basicDay;
	private String empCode;
	private String daily;
	private String attendTime;
	private String attendTypeCode;
	private String quitTime;
	private String quitTypeCode;
	private String leaveTime;
	private String returnTime;
	private String leaveHour;
	private String workHour;
	private String lateHour;
	private String overWorkHour;
	private String nightWorkHour;
	private String closeYn;
	private String attdDayType;
	private String earlyLeaveTime;
	private String empName;
	
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
	public String getDaily() {
		return daily;
	}
	public void setDaily(String daily) {
		this.daily = daily;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getAttendTypeCode() {
		return attendTypeCode;
	}
	public void setAttendTypeCode(String attendTypeCode) {
		this.attendTypeCode = attendTypeCode;
	}
	public String getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	public String getQuitTypeCode() {
		return quitTypeCode;
	}
	public void setQuitTypeCode(String quitTypeCode) {
		this.quitTypeCode = quitTypeCode;
	}
	public String getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(String leaveTime) {
		this.leaveTime = leaveTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	public String getLeaveHour() {
		return leaveHour;
	}
	public void setLeaveHour(String leaveHour) {
		this.leaveHour = leaveHour;
	}
	public String getWorkHour() {
		return workHour;
	}
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
	}
	public String getLateHour() {
		return lateHour;
	}
	public void setLateHour(String lateHour) {
		this.lateHour = lateHour;
	}
	public String getOverWorkHour() {
		return overWorkHour;
	}
	public void setOverWorkHour(String overWorkHour) {
		this.overWorkHour = overWorkHour;
	}
	public String getNightWorkHour() {
		return nightWorkHour;
	}
	public void setNightWorkHour(String nightWorkHour) {
		this.nightWorkHour = nightWorkHour;
	}
	public String getCloseYn() {
		return closeYn;
	}
	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	public String getAttdDayType() {
		return attdDayType;
	}
	public void setAttdDayType(String attdDayType) {
		this.attdDayType = attdDayType;
	}
	public String getEarlyLeaveTime() {
		return earlyLeaveTime;
	}
	public void setEarlyLeaveTime(String earlyLeaveTime) {
		this.earlyLeaveTime = earlyLeaveTime;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
