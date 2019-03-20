package com.test4th.base.to;

import com.test4th.common.annotation.Dataset;
import com.test4th.common.to.BaseBean;

@Dataset(name="dsOvertimeSal")
public class OvertimeSalBean extends BaseBean{

	private String overtimeSalCode;
	private String overtimeSalName;
	private String overtimeSalRate;

	public String getOvertimeSalCode() {
		return overtimeSalCode;
	}
	public void setOvertimeSalCode(String overtimeSalCode) {
		this.overtimeSalCode = overtimeSalCode;
	}
	public String getOvertimeSalName() {
		return overtimeSalName;
	}
	public void setOvertimeSalName(String overtimeSalName) {
		this.overtimeSalName = overtimeSalName;
	}
	public String getOvertimeSalRate() {
		return overtimeSalRate;
	}
	public void setOvertimeSalRate(String overtimeSalRate) {
		this.overtimeSalRate = overtimeSalRate;
	}

}
