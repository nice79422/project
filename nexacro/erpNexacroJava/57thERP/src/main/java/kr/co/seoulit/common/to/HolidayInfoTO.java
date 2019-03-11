package kr.co.seoulit.common.to;

import kr.co.seoulit.common.annotation.Dataset;

@Dataset(name="ds_holidayInfo")
public class HolidayInfoTO extends BaseTO{

	String locdate, seq, dateKind, isHoliday, dateName;
	
	public String getLocdate() {
		return locdate;
	}

	public void setLocdate(String locdate) {
		this.locdate = locdate;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getDateKind() {
		return dateKind;
	}

	public void setDateKind(String dateKind) {
		this.dateKind = dateKind;
	}

	public String getIsHoliday() {
		return isHoliday;
	}

	public void setIsHoliday(String isHoliday) {
		this.isHoliday = isHoliday;
	}

	public String getDateName() {
		return dateName;
	}

	public void setDateName(String dateName) {
		this.dateName = dateName;
	}

}
