package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Column;
import kr.co.seoulit.common.annotation.Dataset;
import kr.co.seoulit.common.to.BaseTO;

@Dataset(name = "dsBaseWorkTime")
public class BaseWorkTimeTO extends BaseTO {

	private String applyYear;
	private String attendTime;
	private String quitTime;
	private String lunchStartTime;
	private String lunchEndTime;
	private String overTime;
	private String nightTime;
	private String dinnerStartTime;
	private String dinnerEndTime;

	@Column(name = "APPLY_YEAR")
	public String getApplyYear() {
		return applyYear;
	}
	@Column(name="APPLY_YEAR")
	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}
	@Column(name = "ATTEND_TIME")
	public String getAttendTime() {
		return attendTime;
	}
	@Column(name = "ATTEND_TIME")
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	@Column(name = "QUIT_TIME")
	public String getQuitTime() {
		return quitTime;
	}
	@Column(name = "QUIT_TIME")
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	@Column(name = "LUNCH_START_TIME")
	public String getLunchStartTime() {
		return lunchStartTime;
	}
	@Column(name = "LUNCH_START_TIME")
	public void setLunchStartTime(String lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}
	@Column(name = "LUNCH_END_TIME")
	public String getLunchEndTime() {
		return lunchEndTime;
	}
	@Column(name = "LUNCH_END_TIME")
	public void setLunchEndTime(String lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}
	@Column(name = "OVER_TIME")
	public String getOverTime() {
		return overTime;
	}
	@Column(name = "OVER_TIME")
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	@Column(name = "NIGHT_TIME")
	public String getNightTime() {
		return nightTime;
	}
	@Column(name = "NIGHT_TIME")
	public void setNightTime(String njightTime) {
		this.nightTime = njightTime;
	}
	@Column(name = "DINNER_START_TIME")
	public String getDinnerStartTime() {
		return dinnerStartTime;
	}
	@Column(name = "DINNER_START_TIME")
	public void setDinnerStartTime(String dinnerStartTime) {
		this.dinnerStartTime = dinnerStartTime;
	}
	@Column(name = "DINNER_END_TIME")
	public String getDinnerEndTime() {
		return dinnerEndTime;
	}
	@Column(name = "DINNER_END_TIME")
	public void setDinnerEndTime(String dinnerEndTime) {
		this.dinnerEndTime = dinnerEndTime;
	}

	@Override
	public String toString() {
		return "BaseWorkTimeTO [applyYear=" + applyYear + ", attendTime=" + attendTime + ", quitTime=" + quitTime
				+ ", lunchStartTime=" + lunchStartTime + ", lunchEndTime=" + lunchEndTime + ", overTime=" + overTime
				+ ", nightTime=" + nightTime + ", dinnerStartTime=" + dinnerStartTime + ", dinnerEndTime="
				+ dinnerEndTime + "]";
	}
}
