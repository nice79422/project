package com.test5th.hr.circumstance.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsBaseWorkTime")
public class BaseWorkTimeBean extends BaseBean{

	private String applyYear;
	private String attendTime;
	private String quitTime;
	private String overTime;
	private String nightTime;
	private String lunchStartTime;
	private String lunchEndTime;
	private String dinnerStartTime;
	private String dinnerEndTime;


	public String getDinnerStartTime() {
		return dinnerStartTime;
	}
	public void setDinnerStartTime(String dinnerStartTime) {
		this.dinnerStartTime = dinnerStartTime;
	}
	public String getDinnerEndTime() {
		return dinnerEndTime;
	}
	public void setDinnerEndTime(String dinnerEndTime) {
		this.dinnerEndTime = dinnerEndTime;
	}
	public String getApplyYear() {
		return applyYear;
	}
	public void setApplyYear(String applyYear) {
		this.applyYear = applyYear;
	}
	public String getAttendTime() {
		return attendTime;
	}
	public void setAttendTime(String attendTime) {
		this.attendTime = attendTime;
	}
	public String getQuitTime() {
		return quitTime;
	}
	public void setQuitTime(String quitTime) {
		this.quitTime = quitTime;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	public String getNightTime() {
		return nightTime;
	}
	public void setNightTime(String nightTime) {
		this.nightTime = nightTime;
	}
	public String getLunchStartTime() {
		return lunchStartTime;
	}
	public void setLunchStartTime(String lunchStartTime) {
		this.lunchStartTime = lunchStartTime;
	}
	public String getLunchEndTime() {
		return lunchEndTime;
	}
	public void setLunchEndTime(String lunchEndTime) {
		this.lunchEndTime = lunchEndTime;
	}

	@Override
	public String toString() {
		return "BaseWorkTimeBean [applyYear=" + applyYear + ", attendTime=" + attendTime + ", quitTime=" + quitTime
				+ ", overTime=" + overTime + ", nightTime=" + nightTime + ", lunchStartTime=" + lunchStartTime
				+ ", lunchEndTime=" + lunchEndTime + "]";
	}
}
