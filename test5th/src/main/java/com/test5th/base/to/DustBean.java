package com.test5th.base.to;


import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name = "dsDust")
public class DustBean extends BaseBean{

	private String stationName;
	private String dataTime;
	private String pm10Grade;


	
	

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getDataTime() {
		return dataTime;
	}
	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}
	public String getPm10Grade() {
		return pm10Grade;
	}
	public void setPm10Grade(String pm10Grade) {
		this.pm10Grade = pm10Grade;
	}
}
