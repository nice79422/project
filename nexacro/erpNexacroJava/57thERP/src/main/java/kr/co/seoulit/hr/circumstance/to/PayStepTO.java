package kr.co.seoulit.hr.circumstance.to;

import kr.co.seoulit.common.annotation.Dataset;

import kr.co.seoulit.common.to.BaseTO;

@Dataset(name="ds_payStep")
public class PayStepTO extends BaseTO{

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
