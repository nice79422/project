package com.test5th.hr.circumstance.to;

import com.test5th.common.annotation.Dataset;
import com.test5th.common.to.BaseBean;

@Dataset(name="dsHobong")
public class HobongBean extends BaseBean{

	String hobong;
	String positionCode;
	String baseSal;
	String bonus;
	String sigub;

	public String getHobong() {
		return hobong;
	}
	public void setHobong(String hobong) {
		this.hobong = hobong;
	}
	public String getPositionCode() {
		return positionCode;
	}
	public void setPositionCode(String positionCode) {
		this.positionCode = positionCode;
	}
	public String getBaseSal() {
		return baseSal;
	}
	public void setBaseSal(String baseSal) {
		this.baseSal = baseSal;
	}
	public String getBonus() {
		return bonus;
	}
	public void setBonus(String bonus) {
		this.bonus = bonus;
	}
	public String getSigub() {
		return sigub;
	}
	public void setSigub(String sigub) {
		this.sigub = sigub;
	}

}
