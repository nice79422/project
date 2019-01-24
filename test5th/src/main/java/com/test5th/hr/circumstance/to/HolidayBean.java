package com.test5th.hr.circumstance.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsHoliday")
public class HolidayBean extends BaseBean{

	private String basicDay;
	private String holidayName;
	private String note;
	private String holidayType;

	public String getBasicDay() {
		return basicDay;
	}
	public void setBasicDay(String basicDay) {
		this.basicDay = basicDay;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getHolidayType() {
		return holidayType;
	}
	public void setHolidayType(String holidayType) {
		this.holidayType = holidayType;
	}
}
