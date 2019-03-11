package kr.co.seoulit.hr.attendance.to;

import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_monthAttdReport")
public class MonthAttdReportTO extends BaseTO{

	private String basicYearMonth;
	private String empCode;
	private String basicWorkHour;
	private String workHour;
	private String overWorkHour;
	private String nightWorkHour;
	private String basicWorkDays;
	private String weekdayWork;
	private String holidayWorkDays;
	private String holidayWorkHour;
	private String holidayOverWorkHour;
	private String holidayNightWorkHour;
	private String absentDays;
	private String lateDays;
	private String lateHour;
	private String leaveEarlyDays;
	private String leaveDays;
	private String closeYn;
	private String attdRestDays;
	private String empName;
	
	public String getBasicYearMonth() {
		return basicYearMonth;
	}
	public void setBasicYearMonth(String basicYearMonth) {
		this.basicYearMonth = basicYearMonth;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getBasicWorkHour() {
		return basicWorkHour;
	}
	public void setBasicWorkHour(String basicWorkHour) {
		this.basicWorkHour = basicWorkHour;
	}
	public String getWorkHour() {
		return workHour;
	}
	public void setWorkHour(String workHour) {
		this.workHour = workHour;
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
	public String getBasicWorkDays() {
		return basicWorkDays;
	}
	public void setBasicWorkDays(String basicWorkDays) {
		this.basicWorkDays = basicWorkDays;
	}
	public String getWeekdayWork() {
		return weekdayWork;
	}
	public void setWeekdayWork(String weekdayWork) {
		this.weekdayWork = weekdayWork;
	}
	public String getHolidayWorkDays() {
		return holidayWorkDays;
	}
	public void setHolidayWorkDays(String holidayWorkDays) {
		this.holidayWorkDays = holidayWorkDays;
	}
	public String getHolidayWorkHour() {
		return holidayWorkHour;
	}
	public void setHolidayWorkHour(String holidayWorkHour) {
		this.holidayWorkHour = holidayWorkHour;
	}
	public String getHolidayOverWorkHour() {
		return holidayOverWorkHour;
	}
	public void setHolidayOverWorkHour(String holidayOverWorkHour) {
		this.holidayOverWorkHour = holidayOverWorkHour;
	}
	public String getHolidayNightWorkHour() {
		return holidayNightWorkHour;
	}
	public void setHolidayNightWorkHour(String holidayNightWorkHour) {
		this.holidayNightWorkHour = holidayNightWorkHour;
	}
	public String getAbsentDays() {
		return absentDays;
	}
	public void setAbsentDays(String absentDays) {
		this.absentDays = absentDays;
	}
	public String getLateDays() {
		return lateDays;
	}
	public void setLateDays(String lateDays) {
		this.lateDays = lateDays;
	}
	public String getLateHour() {
		return lateHour;
	}
	public void setLateHour(String lateHour) {
		this.lateHour = lateHour;
	}
	public String getLeaveEarlyDays() {
		return leaveEarlyDays;
	}
	public void setLeaveEarlyDays(String leaveEarlyDays) {
		this.leaveEarlyDays = leaveEarlyDays;
	}
	public String getLeaveDays() {
		return leaveDays;
	}
	public void setLeaveDays(String leaveDays) {
		this.leaveDays = leaveDays;
	}
	public String getCloseYn() {
		return closeYn;
	}
	public void setCloseYn(String closeYn) {
		this.closeYn = closeYn;
	}
	public String getAttdRestDays() {
		return attdRestDays;
	}
	public void setAttdRestDays(String attdRestDays) {
		this.attdRestDays = attdRestDays;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	
}
